package kg.nail.automatedbusinesssystemforasportsschool.mappers;

import kg.nail.automatedbusinesssystemforasportsschool.entity.Athlete;
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.AthleteDTO;
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.AthleteExampleDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AthleteMapper {
    AthleteExampleDTO toExampleDTO(Athlete athlete);

    Athlete toEntity(AthleteExampleDTO athlete);

    AthleteDTO toDTO(Athlete athlete);
}
