package com.fcs.rooms.entity;

import com.fcs.rooms.entity.vo.Position;
import com.fcs.rooms.entity.vo.Seat;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    @Test
    void shouldBeValidWhenRoomHasValidNameAndSeats() {
        // given
        Position position = new Position("A", 1);
        Seat seat = new Seat("A1", position);
        List<Seat> seats = Arrays.asList(seat);

        Room room = new Room();
        room.setName("Conference Room A");
        room.setSeats(seats);

        // when
        boolean result = room.isValid();

        // then
        assertTrue(result);
    }

    @Test
    void shouldNotBeValidWhenRoomNameIsNull() {
        // given
        Position position = new Position("A", 1);
        Seat seat = new Seat("A1", position);
        List<Seat> seats = Arrays.asList(seat);

        Room room = new Room();
        room.setName(null);
        room.setSeats(seats);

        // when
        boolean result = room.isValid();

        // then
        assertFalse(result);
    }

    @Test
    void shouldNotBeValidWhenRoomNameIsEmpty() {
        // given
        Position position = new Position("A", 1);
        Seat seat = new Seat("A1", position);
        List<Seat> seats = Arrays.asList(seat);

        Room room = new Room();
        room.setName("");
        room.setSeats(seats);

        // when
        boolean result = room.isValid();

        // then
        assertFalse(result);
    }

    @Test
    void shouldNotBeValidWhenSeatsListIsNull() {
        // given
        Room room = new Room();
        room.setName("Conference Room A");
        room.setSeats(null);

        // when
        boolean result = room.isValid();

        // then
        assertFalse(result);
    }

    @Test
    void shouldNotBeValidWhenSeatsListIsEmpty() {
        // given
        Room room = new Room();
        room.setName("Conference Room A");
        room.setSeats(Collections.emptyList());

        // when
        boolean result = room.isValid();

        // then
        assertFalse(result);
    }

    @Test
    void shouldNotBeValidWhenAnySeatIsInvalid() {
        // given
        Position validPosition = new Position("A", 1);
        Seat validSeat = new Seat("A1", validPosition);
        Seat invalidSeat = new Seat("", validPosition);
        List<Seat> seats = Arrays.asList(validSeat, invalidSeat);

        Room room = new Room();
        room.setName("Conference Room A");
        room.setSeats(seats);

        // when
        boolean result = room.isValid();

        // then
        assertFalse(result);
    }

    @Test
    void shouldReturnIdWhenGetIdIsCalled() {
        // given
        Room room = new Room();
        room.setId(42);

        // when
        Integer result = room.getId();

        // then
        assertEquals(42, result);
    }

    @Test
    void shouldReturnNameWhenGetNameIsCalled() {
        // given
        Room room = new Room();
        room.setName("Board Room");

        // when
        String result = room.getName();

        // then
        assertEquals("Board Room", result);
    }

}