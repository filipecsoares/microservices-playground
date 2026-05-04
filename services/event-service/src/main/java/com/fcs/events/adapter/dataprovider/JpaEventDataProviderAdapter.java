package com.fcs.events.adapter.dataprovider;

import com.fcs.events.adapter.dataprovider.jpa.entity.JpaEvent;
import com.fcs.events.adapter.dataprovider.jpa.entity.JpaSession;
import com.fcs.events.adapter.dataprovider.jpa.repository.JpaEventRepository;
import com.fcs.events.adapter.dataprovider.jpa.repository.JpaSessionRepository;
import com.fcs.events.entity.Event;
import com.fcs.events.usecase.gateway.EventGateway;
import com.fcs.events.usecase.model.EventResponseModel;
import com.fcs.events.usecase.model.SessionResponseModel;

import java.util.List;
import java.util.Optional;

public record JpaEventDataProviderAdapter(JpaEventRepository jpaEventRepository, JpaSessionRepository jpaSessionRepository) implements EventGateway {
    @Override
    public Integer create(final Event toCreate) {
        final JpaEvent jpaEvent = new JpaEvent();
        jpaEvent.setName(toCreate.getName());
        jpaEvent.setStartDate(toCreate.getValidity().startDate());
        jpaEvent.setEndDate(toCreate.getValidity().endDate());
        jpaEventRepository.save(jpaEvent);

        toCreate.getSessions().forEach(session -> {
            final JpaSession jpaSession = new JpaSession();
            jpaSession.setName(session.getName());
            jpaSession.setDateTime(session.getStartDateTime());
            jpaSession.setRoomId(session.getRoomId());
            jpaSession.setTicketTypeIdsByQtd(session.getTicketTypeIdsByQtd());
            jpaSession.setEvent(jpaEvent);
            jpaSessionRepository.save(jpaSession);
        });

        return jpaEvent.getId();
    }

    @Override
    public boolean exists(final String name) {
        Optional<JpaEvent> jpaEventOptional = jpaEventRepository.findByName(name);
        return jpaEventOptional.isPresent();
    }

    @Override
    public List<EventResponseModel> getAll() {
        return jpaEventRepository.findAll().stream()
                .map(jpaEvent -> {
                    List<SessionResponseModel> sessions = jpaSessionRepository.findByEventId(jpaEvent.getId())
                            .stream()
                            .map(jpaSession -> new SessionResponseModel(jpaSession.getId(), jpaSession.getName(), jpaSession.getDateTime(), jpaSession.getRoomId(), jpaSession.getTicketTypeIdsByQtd()))
                            .toList();
                    return new EventResponseModel(jpaEvent.getId(), jpaEvent.getName(), jpaEvent.getStartDate(), jpaEvent.getEndDate(), sessions);
                }).toList();
    }

    @Override
    public EventResponseModel getById(final Integer eventId) {
        Optional<JpaEvent> jpaEventOptional = jpaEventRepository.findById(eventId);
        if (jpaEventOptional.isPresent()) {
            JpaEvent jpaEvent = jpaEventOptional.get();
            List<SessionResponseModel> sessions = jpaSessionRepository.findByEventId(jpaEvent.getId()).stream()
                    .map(jpaSession -> new SessionResponseModel(jpaSession.getId(), jpaSession.getName(), jpaSession.getDateTime(), jpaSession.getRoomId(), jpaSession.getTicketTypeIdsByQtd()))
                    .toList();
            return new EventResponseModel(jpaEvent.getId(), jpaEvent.getName(), jpaEvent.getStartDate(), jpaEvent.getEndDate(), sessions);
        }
        return null;
    }

    @Override
    public void delete(final Integer eventId) {
        List<JpaSession> jpaSessions = jpaSessionRepository.findByEventId(eventId);
        if (jpaSessions != null && !jpaSessions.isEmpty()) {
            List<Integer> sessionIds = jpaSessions
                    .stream()
                    .map(JpaSession::getId)
                    .toList();
            sessionIds.forEach(jpaSessionRepository::deleteById);
        }
        jpaEventRepository.deleteById(eventId);
    }
}
