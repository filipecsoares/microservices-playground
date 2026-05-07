package com.fcs.rooms.entity.vo;

import java.time.LocalDateTime;

public record Allocation(LocalDateTime startDateTime, LocalDateTime endDateTime) {

    public boolean isValid() {
        return startDateTime != null && endDateTime != null;
    }
}
