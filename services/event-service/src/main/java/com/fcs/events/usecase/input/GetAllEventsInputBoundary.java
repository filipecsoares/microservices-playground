package com.fcs.events.usecase.input;

import com.fcs.events.usecase.model.EventResponseModel;

import java.util.List;

public interface GetAllEventsInputBoundary {

    List<EventResponseModel> execute();
}
