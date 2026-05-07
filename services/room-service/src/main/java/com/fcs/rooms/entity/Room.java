package com.fcs.rooms.entity;

import com.fcs.rooms.entity.vo.Allocation;
import com.fcs.rooms.entity.vo.Seat;

import java.util.List;

public class Room {

    private Integer id;
    private String name;
    private List<Seat> seats;
    private List<Allocation> allocations;

    public boolean isValid() {
        return name != null && !name.isEmpty()
                && seats != null && !seats.isEmpty() && seats.stream().allMatch(Seat::isValid);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public List<Allocation> getAllocations() {
        return allocations;
    }

    public void setAllocations(List<Allocation> allocations) {
        this.allocations = allocations;
    }
}
