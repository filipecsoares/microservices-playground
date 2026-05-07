package com.fcs.events.adapter.entrypoint.dto;

import java.time.LocalDateTime;
import java.util.Map;

public record SessionDto(String name, LocalDateTime startDateTime, LocalDateTime endDateTime, Integer roomId, Map<Integer, Integer> ticketTypeIdsByQtd) {
}
