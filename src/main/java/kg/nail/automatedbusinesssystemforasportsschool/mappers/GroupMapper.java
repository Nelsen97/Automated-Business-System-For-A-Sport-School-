package kg.nail.automatedbusinesssystemforasportsschool.mappers;

import kg.nail.automatedbusinesssystemforasportsschool.entity.Athlete;
import kg.nail.automatedbusinesssystemforasportsschool.entity.Group;
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.AddedAthletesToGroupDTO;
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.CreateGroupDTO;
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.GroupDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    @Mapping(target = "athletes", expression = "java(mapAthleteIds(group.getAthletes()))")
    @Mapping(target = "trainerId", source = "trainer.id")
    GroupDTO toGroupDTO(Group group);

    @Mapping(target = "athletes", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "trainer", ignore = true)
    Group toEntity(GroupDTO group);

    @Mapping(target = "trainer.id", source = "trainerId")
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "athletes", ignore = true)
    Group createDTOToEntity(CreateGroupDTO createGroupDTO);

    default List<Long> mapAthleteIds(List<Athlete> athletes) {
        return athletes.stream()
                .map(Athlete::getId)
                .toList();
    }

    AddedAthletesToGroupDTO toAddAthletesDTO(Group group);
}
