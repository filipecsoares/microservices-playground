package com.fcs.events.adapter.presenter;

import com.fcs.events.usecase.exception.BusinessException;
import com.fcs.events.usecase.presenter.EventDeletedPresenter;
import org.springframework.stereotype.Component;

@Component
public class EventDeletedResponseFormatter implements EventDeletedPresenter {

    @Override
    public void prepareFailView(String error) {
        throw new BusinessException(error);
    }
}
