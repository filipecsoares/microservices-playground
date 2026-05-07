package com.fcs.events.adapter.presenter;

import com.fcs.events.usecase.exception.BusinessException;
import com.fcs.events.usecase.model.EventResponseModel;
import com.fcs.events.usecase.presenter.EventPresenter;
import org.springframework.stereotype.Component;

@Component
public class EventResponseFormatter implements EventPresenter {

    @Override
    public EventResponseModel prepareSuccessView(EventResponseModel eventResponseModel) {
        return eventResponseModel;
    }

    @Override
    public void prepareFailView(String error) {
        throw new BusinessException(error);
    }
}
