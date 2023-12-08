package kg.nail.automatedbusinesssystemforasportsschool.mappers;

import kg.nail.automatedbusinesssystemforasportsschool.entity.Athlete;
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.AthleteDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AthleteMapper {

    @Mapping(target = "groupId", source = "group.id")
    @Mapping(target = "dateOfBirth", source = "dateOfBirth", dateFormat = "dd-MM-yyyy")
    AthleteDTO toDTO(Athlete athlete);

    @Mapping(target = "paymentAmount", ignore = true)
    @Mapping(target = "group.id", source = "groupId")
    @Mapping(target = "parents", ignore = true)
    Athlete toEntity(AthleteDTO athleteDTO) ;


}
