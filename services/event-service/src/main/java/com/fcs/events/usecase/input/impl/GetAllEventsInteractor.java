package com.fcs.events.usecase.input.impl;

import com.fcs.events.usecase.gateway.EventGateway;
import com.fcs.events.usecase.input.GetAllEventsInputBoundary;
import com.fcs.events.usecase.model.EventResponseModel;
import com.fcs.events.usecase.presenter.AllEventsPresenter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetAllEventsInteractor implements GetAllEventsInputBoundary {

    private final AllEventsPresenter allEventsPresenter;
    private final EventGateway eventGateway;

    public GetAllEventsInteractor(final AllEventsPresenter allEventsPresenter, final EventGateway eventGateway) {
        this.allEventsPresenter = allEventsPresenter;
        this.eventGateway = eventGateway;
    }

    @Override
    public List<EventResponseModel> execute() {
        List<EventResponseModel> allEvents = new ArrayList<>();
        try {
            allEvents = eventGateway.getAll();
            return allEventsPresenter.prepareSuccessView(allEvents);
        } catch (Exception e) {
            allEventsPresenter.prepareFailView("Ocorreu um erro interno ao obter todos os eventos cadastrados.");
        }
        return allEvents;
    }
}
