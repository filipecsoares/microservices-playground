package com.fcs.events.adapter.dataprovider.http.openfeign;

import com.fcs.events.adapter.dataprovider.http.openfeign.model.ticket.TicketResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ticket-client", url = "${spring.cloud.openfeign.client.config.ticket-service.api.baseurl}")
public interface TicketFeignClient {

    @GetMapping("/ticket/{id}")
    TicketResponseEntity getTicket(@PathVariable("id") Integer ticketId);
}
