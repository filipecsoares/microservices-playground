package com.fcs.events.usecase.input.impl;

import com.fcs.events.usecase.gateway.EventGateway;
import com.fcs.events.usecase.input.DeleteEventInputBoundary;
import com.fcs.events.usecase.presenter.EventDeletedPresenter;
import org.springframework.stereotype.Service;

@Service
public class DeleteEventInteractor implements DeleteEventInputBoundary {

    private final EventDeletedPresenter eventDeletedPresenter;
    private final EventGateway eventGateway;

    public DeleteEventInteractor(final EventDeletedPresenter eventDeletedPresenter, final EventGateway eventGateway) {
        this.eventDeletedPresenter = eventDeletedPresenter;
        this.eventGateway = eventGateway;
    }

    @Override
    public void execute(Integer eventId) {
        try {
            eventGateway.delete(eventId);
        } catch (Exception e) {
            eventDeletedPresenter.prepareFailView("Ocorreu um erro interno ao excluir o evento.");
        }
    }
}
