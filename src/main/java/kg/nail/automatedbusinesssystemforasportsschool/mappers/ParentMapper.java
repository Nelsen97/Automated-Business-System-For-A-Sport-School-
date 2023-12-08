package kg.nail.automatedbusinesssystemforasportsschool.mappers;

import kg.nail.automatedbusinesssystemforasportsschool.entity.Parent;
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.ParentDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ParentMapper {
    ParentDTO toDTO(Parent parent);

    @Mapping(target = "children", ignore = true)
    @InheritInverseConfiguration
    Parent toEntity(ParentDTO parentDTO);
}
