package com.fcs.events.adapter.presenter;

import com.fcs.events.entity.Event;
import com.fcs.events.usecase.exception.BusinessException;
import com.fcs.events.usecase.model.EventCreatedResponseModel;
import com.fcs.events.usecase.presenter.EventCreatedPresenter;
import org.springframework.stereotype.Component;

@Component
public class EventCreatedResponseFormatter implements EventCreatedPresenter {
    @Override
    public EventCreatedResponseModel prepareSuccessView(final Event event) {
        return new EventCreatedResponseModel(event.getId());
    }

    @Override
    public void prepareFailView(final String error) {
        throw new BusinessException(error);
    }
}
