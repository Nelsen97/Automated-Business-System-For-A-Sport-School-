package kg.nail.automatedbusinesssystemforasportsschool.mappers;

import kg.nail.automatedbusinesssystemforasportsschool.entity.Event;
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.EventDTO;
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.EventRegisterDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mapping(target = "groupId", source = "group.id")
    EventDTO toEventDTO(Event event);

    @Mapping(target = "group.id", source = "groupId")
    Event fromEventDTOToEntity(EventDTO eventDTO);

    @Mapping(target = "group.id", source = "groupId")
    @Mapping(target = "id", ignore = true)
    Event fromEventRegisterToEntity(EventRegisterDTO eventRegisterDTO);
}
