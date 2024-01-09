package kg.nail.automatedbusinesssystemforasportsschool.service;

import kg.nail.automatedbusinesssystemforasportsschool.web.dto.EventDTO;
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.EventRegisterDTO;

import java.util.List;

public interface EventService {

    EventRegisterDTO registerEvent(EventRegisterDTO event);

    EventDTO getEventById(Long eventId);

    List<EventDTO> getAllEventsByGroupId(Long groupId);

    void delete(Long eventId);

    EventDTO update(Long eventId);
}
