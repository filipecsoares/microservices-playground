package com.fcs.rooms.entity.vo;

public record Seat(String name, Position position) {

    public boolean isValid() {
        return name != null && !name.isEmpty()
                && position != null && position.isValid();
    }
}
