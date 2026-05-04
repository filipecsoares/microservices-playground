package com.fcs.events.adapter.dataprovider;

import com.fcs.events.adapter.dataprovider.jpa.entity.JpaEvent;
import com.fcs.events.adapter.dataprovider.jpa.entity.JpaSession;
import com.fcs.events.adapter.dataprovider.jpa.repository.JpaEventRepository;
import com.fcs.events.adapter.dataprovider.jpa.repository.JpaSessionRepository;
import com.fcs.events.usecase.model.EventResponseModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JpaEventDataProviderAdapterTest {

    @Mock
    private JpaEventRepository jpaEventRepository;

    @Mock
    private JpaSessionRepository jpaSessionRepository;

    @InjectMocks
    private JpaEventDataProviderAdapter adapter;

    @Test
    void shouldReturnTrueWhenEventExists() {
        // Given
        final String eventName = "Tech Conference 2026";
        final JpaEvent jpaEvent = new JpaEvent();
        jpaEvent.setId(1);
        jpaEvent.setName(eventName);

        when(jpaEventRepository.findByName(eventName)).thenReturn(Optional.of(jpaEvent));

        // When
        final boolean exists = adapter.exists(eventName);

        // Then
        assertTrue(exists);
        verify(jpaEventRepository, times(1)).findByName(eventName);
    }

    @Test
    void shouldReturnFalseWhenEventDoesNotExist() {
        // Given
        final String eventName = "Non-existent Event";
        when(jpaEventRepository.findByName(eventName)).thenReturn(Optional.empty());

        // When
        final boolean exists = adapter.exists(eventName);

        // Then
        assertFalse(exists);
        verify(jpaEventRepository, times(1)).findByName(eventName);
    }

    @Test
    void shouldReturnEmptyListWhenNoEventsExist() {
        // Given
        when(jpaEventRepository.findAll()).thenReturn(List.of());

        // When
        final List<EventResponseModel> result = adapter.getAll();

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(jpaEventRepository, times(1)).findAll();
        verify(jpaSessionRepository, never()).findByEventId(any());
    }

    @Test
    void shouldDeleteEventAndAssociatedSessions() {
        // Given
        final Integer eventId = 1;
        final JpaSession session1 = new JpaSession();
        session1.setId(10);
        final JpaSession session2 = new JpaSession();
        session2.setId(11);

        when(jpaSessionRepository.findByEventId(eventId)).thenReturn(List.of(session1, session2));

        // When
        adapter.delete(eventId);

        // Then
        verify(jpaSessionRepository, times(1)).findByEventId(eventId);
        verify(jpaSessionRepository, times(1)).deleteById(10);
        verify(jpaSessionRepository, times(1)).deleteById(11);
        verify(jpaEventRepository, times(1)).deleteById(eventId);
    }

}