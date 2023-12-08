package kg.nail.automatedbusinesssystemforasportsschool.mappers;

import kg.nail.automatedbusinesssystemforasportsschool.entity.Athlete;
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.AthleteDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AthleteMapper {

    @Mapping(target = "groupId", source = "group.id")
    AthleteDTO toDTO(Athlete athlete);

    @Mapping(target = "paymentAmount", ignore = true)
    @InheritInverseConfiguration
    Athlete toEntity(AthleteDTO athleteDTO);

}
