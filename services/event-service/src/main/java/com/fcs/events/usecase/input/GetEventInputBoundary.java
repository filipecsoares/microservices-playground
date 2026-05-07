package com.fcs.events.usecase.input;

import com.fcs.events.usecase.model.EventResponseModel;

public interface GetEventInputBoundary {

    EventResponseModel execute(Integer eventId);
}
