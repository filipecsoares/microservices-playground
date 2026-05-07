package com.fcs.events.usecase.presenter;

import com.fcs.events.usecase.model.EventResponseModel;

import java.util.List;

public interface AllEventsPresenter {

    List<EventResponseModel> prepareSuccessView(List<EventResponseModel> allEvents);

    void prepareFailView(String error);
}

