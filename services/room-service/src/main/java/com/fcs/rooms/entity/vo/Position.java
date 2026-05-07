package com.fcs.rooms.entity.vo;

public record Position(String row, Integer num) {

    public boolean isValid() {
        return row != null && !row.isEmpty()
                && num != null;
    }
}
