package com.fcs.events.adapter.dataprovider;

import com.fcs.events.adapter.dataprovider.http.openfeign.TicketFeignClient;
import com.fcs.events.adapter.dataprovider.http.openfeign.model.ticket.TicketResponseEntity;
import com.fcs.events.usecase.model.TicketModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TicketDataProviderByExternalApiAdapterTest {

    @Mock
    private TicketFeignClient ticketFeignClient;

    @InjectMocks
    private TicketDataProviderByExternalApiAdapter adapter;

    @Test
    void shouldFindByWhenTicketExists() {
        // Arrange
        final Integer ticketId = 1;
        final String ticketName = "VIP Pass";

        TicketResponseEntity ticketResponse = new TicketResponseEntity(ticketId, ticketName, "VIP", null);
        when(ticketFeignClient.getTicket(ticketId)).thenReturn(ticketResponse);

        // Act
        TicketModel result = adapter.findBy(ticketId);

        // Assert
        assertNotNull(result);
        assertEquals(ticketName, result.name());
        assertTrue(result.exists());
        verify(ticketFeignClient, times(1)).getTicket(ticketId);
    }

    @Test
    void shouldFindByWhenTicketDoesNotExist() {
        // Arrange
        final Integer ticketId = 999;
        when(ticketFeignClient.getTicket(ticketId)).thenReturn(null);

        // Act
        TicketModel result = adapter.findBy(ticketId);

        // Assert
        assertNotNull(result);
        assertEquals("", result.name());
        assertFalse(result.exists());
        verify(ticketFeignClient, times(1)).getTicket(ticketId);
    }

}