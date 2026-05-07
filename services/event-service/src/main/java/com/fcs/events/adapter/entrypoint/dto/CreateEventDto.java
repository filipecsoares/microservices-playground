package com.fcs.events.adapter.entrypoint.dto;

import java.time.LocalDateTime;
import java.util.List;

public record CreateEventDto(String name, LocalDateTime startDate, LocalDateTime endDate, List<SessionDto> sessions) {
}
