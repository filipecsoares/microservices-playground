package com.fcs.events.usecase.presenter;

import com.fcs.events.usecase.model.EventResponseModel;

public interface EventPresenter {

    EventResponseModel prepareSuccessView(final EventResponseModel eventResponseModel);

    void prepareFailView(final String error);
}
