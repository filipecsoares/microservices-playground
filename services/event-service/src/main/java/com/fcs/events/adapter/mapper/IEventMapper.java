package com.fcs.events.adapter.mapper;

import com.fcs.events.adapter.entrypoint.dto.CreateEventDto;
import com.fcs.events.entity.Event;
import com.fcs.events.entity.Session;
import com.fcs.events.entity.vo.Validity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface IEventMapper {

    default Event createEventDtoToEvent(CreateEventDto requestDto) {
        List<Session> sessions = requestDto.sessions().stream()
                .map(sessionDto -> new Session(sessionDto.name(), sessionDto.startDateTime(), sessionDto.endDateTime(), sessionDto.roomId(), sessionDto.ticketTypeIdsByQtd())).toList();
        return new Event(requestDto.name(), new Validity(requestDto.startDate(), requestDto.endDate()), sessions);
    }
}
