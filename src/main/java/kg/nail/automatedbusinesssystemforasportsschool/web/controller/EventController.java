package kg.nail.automatedbusinesssystemforasportsschool.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kg.nail.automatedbusinesssystemforasportsschool.entity.Event;
import kg.nail.automatedbusinesssystemforasportsschool.service.EventService;
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.EventDTO;
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.EventRegisterDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
@Tag(name = "Event API", description = "API тренировок")
@Slf4j
public class EventController {

    private final EventService eventService;

    @Operation(summary = "Get a event by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the event",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Event.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Event not found",
                    content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable("id") Long eventId) {
        return new ResponseEntity<>(eventService.getEventById(eventId), HttpStatus.OK);
    }

    @PostMapping("/register/event")
    public ResponseEntity<EventRegisterDTO> registerEvent(@Valid @RequestBody EventRegisterDTO event) {
        return new ResponseEntity<>(eventService.registerEvent(event), HttpStatus.OK);
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<List<EventDTO>> getAllEventsByGroupId(@PathVariable("id") Long groupId) {
        return new ResponseEntity<>(eventService.getAllEventsByGroupId(groupId), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EventDTO> updateEventById(@PathVariable("id") Long eventId) {
        return new ResponseEntity<>(eventService.update(eventId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteEventById(@PathVariable("id") Long eventId) {
        eventService.delete(eventId);

        return ResponseEntity.ok().build();
    }
}
