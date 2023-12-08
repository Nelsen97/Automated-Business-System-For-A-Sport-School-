package kg.nail.automatedbusinesssystemforasportsschool.mappers;

import kg.nail.automatedbusinesssystemforasportsschool.entity.Group;
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.GroupDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    GroupDTO toDTO(Group group);

    @Mapping(target = "athletes", ignore = true)
    Group toEntity(GroupDTO group);
}
