package com.fcs.events.usecase.input.impl;

import com.fcs.events.usecase.gateway.EventGateway;
import com.fcs.events.usecase.model.EventResponseModel;
import com.fcs.events.usecase.presenter.AllEventsPresenter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetAllEventsInteractorTest {

    @Mock
    private AllEventsPresenter allEventsPresenter;
    @Mock
    private EventGateway eventGateway;

    @InjectMocks
    private GetAllEventsInteractor getAllEventsInteractor;

    @Test
    public void given_NoParameters_whenGatewayReturnsEvents_should_call_prepareSuccessView() {
        // Given
        LocalDateTime startDate = LocalDateTime.of(2024, 2, 3, 0, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2024, 2, 5, 0, 0, 0);
        List<EventResponseModel> events = new ArrayList<>();
        events.add(new EventResponseModel(1, "Event 1", startDate, endDate, new ArrayList<>()));
        events.add(new EventResponseModel(2, "Event 2", startDate, endDate, new ArrayList<>()));

        given(eventGateway.getAll()).willReturn(events);
        given(allEventsPresenter.prepareSuccessView(events)).willReturn(events);

        // When
        List<EventResponseModel> result = getAllEventsInteractor.execute();

        // Then
        assertEquals(2, result.size());
        verify(eventGateway, times(1)).getAll();
        verify(allEventsPresenter, times(1)).prepareSuccessView(events);
        verify(allEventsPresenter, never()).prepareFailView(any());
    }

    @Test
    public void given_NoParameters_whenGatewayThrowsException_should_call_prepareFailView() {
        // Given
        doThrow(new RuntimeException("Error fetching events"))
                .when(eventGateway).getAll();

        // When
        List<EventResponseModel> result = getAllEventsInteractor.execute();

        // Then
        assertTrue(result.isEmpty());
        verify(eventGateway, times(1)).getAll();
        verify(allEventsPresenter, times(1))
                .prepareFailView("Ocorreu um erro interno ao obter todos os eventos cadastrados.");
    }

    @Test
    public void given_NoParameters_whenGatewayReturnsEmptyList_should_return_empty_list() {
        // Given
        List<EventResponseModel> emptyList = new ArrayList<>();
        given(eventGateway.getAll()).willReturn(emptyList);
        given(allEventsPresenter.prepareSuccessView(emptyList)).willReturn(emptyList);

        // When
        List<EventResponseModel> result = getAllEventsInteractor.execute();

        // Then
        assertTrue(result.isEmpty());
        verify(eventGateway, times(1)).getAll();
        verify(allEventsPresenter, times(1)).prepareSuccessView(emptyList);
    }
}
