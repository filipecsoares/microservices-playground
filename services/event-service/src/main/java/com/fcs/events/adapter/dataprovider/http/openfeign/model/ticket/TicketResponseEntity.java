package com.fcs.events.adapter.dataprovider.http.openfeign.model.ticket;

public record TicketResponseEntity(Integer id, String name, String ticketType, TicketUnitPriceResponseEntity unitPrice) {
}
