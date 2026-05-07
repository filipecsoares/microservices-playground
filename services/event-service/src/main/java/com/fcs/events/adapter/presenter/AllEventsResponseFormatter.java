package com.fcs.events.adapter.presenter;

import com.fcs.events.usecase.exception.BusinessException;
import com.fcs.events.usecase.model.EventResponseModel;
import com.fcs.events.usecase.presenter.AllEventsPresenter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AllEventsResponseFormatter implements AllEventsPresenter {

    @Override
    public List<EventResponseModel> prepareSuccessView(List<EventResponseModel> allEvents) {
        return allEvents;
    }

    @Override
    public void prepareFailView(String error) {
        throw new BusinessException(error);
    }
}
