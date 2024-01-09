package kg.nail.automatedbusinesssystemforasportsschool.mappers;

import kg.nail.automatedbusinesssystemforasportsschool.entity.Athlete;
import kg.nail.automatedbusinesssystemforasportsschool.enums.Role;
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.AthleteDTO;
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.TrainerDTO;
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.TrainerRegisterDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AthleteMapper {

    @Mapping(target = "groupId", source = "group.id")
    @Mapping(target = "dateOfBirth", source = "dateOfBirth", dateFormat = "dd-MM-yyyy")
    AthleteDTO toDTO(Athlete athlete);

    @Mapping(target = "paymentAmount", ignore = true)
    @Mapping(target = "group.id", source = "groupId")
    @Mapping(target = "parents", ignore = true)
    @Mapping(target = "enrollmentDate", ignore = true)
    @Mapping(target = "active", constant = "true")
    @Mapping(target = "role", constant = "ROLE_ATHLETE")
    Athlete toEntityFromAthleteDTO(AthleteDTO athleteDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "enrollmentDate", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "source", ignore = true)
    @Mapping(target = "role", constant = "ROLE_ATHLETE")
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "group.id", source = "groupId")
    @Mapping(target = "paymentAmount", ignore = true)
    void updateAthleteFromDTO(AthleteDTO athleteDTO, @MappingTarget Athlete athlete);

    @Mapping(target = "enrollmentDate", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "group", ignore = true)
    @Mapping(target = "paymentAmount", ignore = true)
    @Mapping(target = "school", ignore = true)
    @Mapping(target = "height", ignore = true)
    @Mapping(target = "weight", ignore = true)
    @Mapping(target = "parents", ignore = true)
    Athlete toEntityFromTrainerRegisterDTO(TrainerRegisterDTO trainer);


    @Mapping(target = "groups", expression = "java(mapTrainerGroups(athlete))")
    TrainerDTO toTrainerDTOFromEntity(Athlete athlete);

    default List<Long> mapTrainerGroups(Athlete athlete) {
        if (athlete.getRole() == Role.ROLE_TRAINER && athlete.getGroup() != null) {
            return Collections.singletonList(athlete.getGroup().getId());
        } else {
            return Collections.emptyList();
        }
    }
}
