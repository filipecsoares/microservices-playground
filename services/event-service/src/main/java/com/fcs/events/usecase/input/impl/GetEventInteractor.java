package com.fcs.events.usecase.input.impl;

import com.fcs.events.usecase.gateway.EventGateway;
import com.fcs.events.usecase.input.GetEventInputBoundary;
import com.fcs.events.usecase.model.EventResponseModel;
import com.fcs.events.usecase.presenter.EventPresenter;
import org.springframework.stereotype.Service;

@Service
public class GetEventInteractor implements GetEventInputBoundary {

    private final EventPresenter eventPresenter;
    private final EventGateway eventGateway;

    public GetEventInteractor(final EventPresenter eventPresenter, final EventGateway eventGateway) {
        this.eventPresenter = eventPresenter;
        this.eventGateway = eventGateway;
    }

    @Override
    public EventResponseModel execute(Integer eventId) {
        try {
            return eventPresenter.prepareSuccessView(eventGateway.getById(eventId));
        } catch (Exception e) {
            eventPresenter.prepareFailView("Ocorreu um erro interno ao obter os dados do evento.");
        }
        return null;
    }
}
