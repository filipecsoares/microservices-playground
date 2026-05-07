package com.fcs.events.usecase.input.impl;

import com.fcs.events.usecase.gateway.EventGateway;
import com.fcs.events.usecase.model.EventResponseModel;
import com.fcs.events.usecase.presenter.EventPresenter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetEventInteractorTest {

    @Mock
    private EventPresenter eventPresenter;
    @Mock
    private EventGateway eventGateway;

    @InjectMocks
    private GetEventInteractor getEventInteractor;

    @Test
    public void given_EventId_whenEventExists_should_call_prepareSuccessView() {
        // Given
        Integer eventId = 1;
        LocalDateTime startDate = LocalDateTime.of(2024, 2, 3, 0, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2024, 2, 5, 0, 0, 0);
        EventResponseModel eventResponseModel = new EventResponseModel(1, "Event 1", startDate, endDate, new ArrayList<>());

        given(eventGateway.getById(eventId)).willReturn(eventResponseModel);
        given(eventPresenter.prepareSuccessView(eventResponseModel)).willReturn(eventResponseModel);

        // When
        EventResponseModel result = getEventInteractor.execute(eventId);

        // Then
        assertEquals(eventResponseModel, result);
        verify(eventGateway, times(1)).getById(eventId);
        verify(eventPresenter, times(1)).prepareSuccessView(eventResponseModel);
        verify(eventPresenter, never()).prepareFailView(any());
    }

    @Test
    public void given_EventId_whenGatewayThrowsException_should_call_prepareFailView() {
        // Given
        Integer eventId = 1;
        doThrow(new RuntimeException("Event not found"))
                .when(eventGateway).getById(eventId);

        // When
        EventResponseModel result = getEventInteractor.execute(eventId);

        // Then
        assertNull(result);
        verify(eventGateway, times(1)).getById(eventId);
        verify(eventPresenter, times(1))
                .prepareFailView("Ocorreu um erro interno ao obter os dados do evento.");
    }

    @Test
    public void given_EventId_whenEventDoesNotExist_should_return_null() {
        // Given
        Integer eventId = 999;
        given(eventGateway.getById(eventId)).willReturn(null);

        // When
        EventResponseModel result = getEventInteractor.execute(eventId);

        // Then
        assertNull(result);
        verify(eventGateway, times(1)).getById(eventId);
        verify(eventPresenter, times(1)).prepareSuccessView(null);
    }
}
