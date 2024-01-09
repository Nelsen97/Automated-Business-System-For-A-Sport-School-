package kg.nail.automatedbusinesssystemforasportsschool.service.impl;

import kg.nail.automatedbusinesssystemforasportsschool.entity.Event;
import kg.nail.automatedbusinesssystemforasportsschool.exception.CustomException;
import kg.nail.automatedbusinesssystemforasportsschool.mappers.EventMapper;
import kg.nail.automatedbusinesssystemforasportsschool.repository.EventRepository;
import kg.nail.automatedbusinesssystemforasportsschool.service.EventService;
import kg.nail.automatedbusinesssystemforasportsschool.service.GroupService;
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.EventDTO;
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.EventRegisterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final GroupService groupService;
    private final EventMapper eventMapper;

    @Override
    public EventRegisterDTO registerEvent(EventRegisterDTO event) {

        groupService.getGroupById(event.getGroupId());
        eventRepository.save(eventMapper.fromEventRegisterToEntity(event));

        return event;
    }

    @Override
    public EventDTO getEventById(Long eventId) {
        return eventMapper.toEventDTO(findEventById(eventId));
    }

    @Override
    public List<EventDTO> getAllEventsByGroupId(Long groupId) {
        List<Event> events = eventRepository.findAllByGroupId(groupId);

        return events.stream()
                .map(eventMapper::toEventDTO)
                .toList();
    }

    @Override
    public void delete(Long eventId) {
        eventRepository.delete(findEventById(eventId));
    }

    @Override
    public EventDTO update(Long eventId) {
        Event event = findEventById(eventId);

        return eventMapper.toEventDTO(eventRepository.save(event));
    }

    private Event findEventById(Long eventId) {
        return eventRepository.findById(eventId).orElseThrow(
                () -> new CustomException("There is no such event")
        );
    }
}
