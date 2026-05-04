package com.fcs.events.adapter.dataprovider.jpa.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Map;

@Entity(name = "sessions")
public class JpaSession {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private LocalDateTime dateTime;
    private Integer roomId;
    @ElementCollection
    @MapKeyColumn(name = "ticket_type_id")
    @Column(name = "tickets_available_for_type")
    private Map<Integer, Integer> ticketTypeIdsByQtd;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private JpaEvent event;

    public JpaSession() {
    }

    public JpaSession(final Integer id, final String name, final LocalDateTime dateTime, final Integer roomId, final Map<Integer, Integer> ticketTypeIdsByQtd, final JpaEvent event) {
        this.id = id;
        this.name = name;
        this.dateTime = dateTime;
        this.roomId = roomId;
        this.ticketTypeIdsByQtd = ticketTypeIdsByQtd;
        this.event = event;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(final LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(final Integer roomId) {
        this.roomId = roomId;
    }

    public Map<Integer, Integer> getTicketTypeIdsByQtd() {
        return ticketTypeIdsByQtd;
    }

    public void setTicketTypeIdsByQtd(final Map<Integer, Integer> ticketTypeIdsByQtd) {
        this.ticketTypeIdsByQtd = ticketTypeIdsByQtd;
    }

    public JpaEvent getEvent() {
        return event;
    }

    public void setEvent(final JpaEvent event) {
        this.event = event;
    }
}