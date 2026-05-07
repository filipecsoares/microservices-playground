package com.fcs.events.usecase.input.impl;

import com.fcs.events.usecase.gateway.EventGateway;
import com.fcs.events.usecase.presenter.EventDeletedPresenter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteEventInteractorTest {

    @Mock
    private EventDeletedPresenter eventDeletedPresenter;
    @Mock
    private EventGateway eventGateway;

    @InjectMocks
    private DeleteEventInteractor deleteEventInteractor;

    @Test
    public void given_EventId_whenEventIsDeleted_should_call_gateway_delete() {
        // Given
        Integer eventId = 1;

        // When
        deleteEventInteractor.execute(eventId);

        // Then
        verify(eventGateway, times(1)).delete(eventId);
        verify(eventDeletedPresenter, never()).prepareFailView(any());
    }

    @Test
    public void given_EventId_whenGatewayThrowsException_should_call_prepareFailView() {
        // Given
        Integer eventId = 1;
        doThrow(new RuntimeException("Error deleting event"))
                .when(eventGateway).delete(eventId);

        // When
        deleteEventInteractor.execute(eventId);

        // Then
        verify(eventGateway, times(1)).delete(eventId);
        verify(eventDeletedPresenter, times(1))
                .prepareFailView("Ocorreu um erro interno ao excluir o evento.");
    }
}
