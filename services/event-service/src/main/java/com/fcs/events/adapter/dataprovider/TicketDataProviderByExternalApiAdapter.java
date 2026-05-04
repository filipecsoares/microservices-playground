package com.fcs.events.adapter.dataprovider;

import com.fcs.events.adapter.dataprovider.http.openfeign.TicketFeignClient;
import com.fcs.events.adapter.dataprovider.http.openfeign.model.ticket.TicketResponseEntity;
import com.fcs.events.usecase.gateway.TicketGateway;
import com.fcs.events.usecase.model.TicketModel;
import org.springframework.stereotype.Component;

@Component
public class TicketDataProviderByExternalApiAdapter implements TicketGateway {

    private final TicketFeignClient ticketFeignClient;

    public TicketDataProviderByExternalApiAdapter(final TicketFeignClient ticketFeignClient) {
        this.ticketFeignClient = ticketFeignClient;
    }

    @Override
    public TicketModel findBy(Integer id) {
        TicketResponseEntity ticketResponseEntity = ticketFeignClient.getTicket(id);
        if (ticketResponseEntity != null) {
            return new TicketModel(ticketResponseEntity.name(), true);
        }
        return new TicketModel("", false);
    }
}
