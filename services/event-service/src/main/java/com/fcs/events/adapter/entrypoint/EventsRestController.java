package com.fcs.events.adapter.entrypoint;

import com.fcs.events.adapter.entrypoint.dto.CreateEventDto;
import com.fcs.events.adapter.mapper.IEventMapper;
import com.fcs.events.usecase.input.CreateEventInputBoundary;
import com.fcs.events.usecase.input.DeleteEventInputBoundary;
import com.fcs.events.usecase.input.GetAllEventsInputBoundary;
import com.fcs.events.usecase.input.GetEventInputBoundary;
import com.fcs.events.usecase.model.EventCreatedResponseModel;
import com.fcs.events.usecase.model.EventResponseModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventsRestController {

    private final CreateEventInputBoundary createEventInputBoundary;
    private final GetAllEventsInputBoundary getAllEventsInputBoundary;
    private final GetEventInputBoundary getEventInputBoundary;
    private final DeleteEventInputBoundary deleteEventInputBoundary;
    private final IEventMapper mapper;

    public EventsRestController(final CreateEventInputBoundary createEventInputBoundary, final GetAllEventsInputBoundary getAllEventsInputBoundary, final GetEventInputBoundary getEventInputBoundary, final DeleteEventInputBoundary deleteEventInputBoundary, final IEventMapper mapper) {
        this.createEventInputBoundary = createEventInputBoundary;
        this.getAllEventsInputBoundary = getAllEventsInputBoundary;
        this.getEventInputBoundary = getEventInputBoundary;
        this.deleteEventInputBoundary = deleteEventInputBoundary;
        this.mapper = mapper;
    }

    @PostMapping("/event")
    @Operation(summary = "Create a new event with its sessions",
            description = "Given valid payload, create a new event with its sessions and return the new event ID",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Success"),
                    @ApiResponse(responseCode = "422", description = "When some business error occur"),
                    @ApiResponse(responseCode = "500", description = "When an internal error occur")
            })
    public ResponseEntity<EventCreatedResponseModel> create(@RequestBody CreateEventDto requestDto) {
        EventCreatedResponseModel eventCreatedResponseModel = createEventInputBoundary.execute(mapper.createEventDtoToEvent(requestDto));
        return new ResponseEntity<>(eventCreatedResponseModel, HttpStatus.CREATED);
    }

    @GetMapping("/events")
    @Operation(summary = "Get all events details",
            description = "Given valid payload, return all events details with its sessions",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "422", description = "When some business error occur"),
                    @ApiResponse(responseCode = "500", description = "When an internal error occur")
            })
    public ResponseEntity<List<EventResponseModel>> getAll() {
        List<EventResponseModel> allEvents = getAllEventsInputBoundary.execute();
        return new ResponseEntity<>(allEvents, HttpStatus.OK);
    }

    @GetMapping("/event/{id}")
    @Operation(summary = "Get event details with its sessions by ID",
            description = "Given valid payload, return event details with its sessions by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "422", description = "When some business error occur"),
                    @ApiResponse(responseCode = "500", description = "When an internal error occur")
            })
    public ResponseEntity<EventResponseModel> getEvent(@PathVariable("id") Integer eventId) {
        EventResponseModel event = getEventInputBoundary.execute(eventId);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @DeleteMapping("/event/{id}")
    @Operation(summary = "Delete the event and its sessions",
            description = "Given valid payload, delete event details with its sessions by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "422", description = "When some business error occur"),
                    @ApiResponse(responseCode = "500", description = "When an internal error occur")
            })
    public ResponseEntity<Void> deleteEvent(@PathVariable("id") Integer eventId) {
        deleteEventInputBoundary.execute(eventId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
