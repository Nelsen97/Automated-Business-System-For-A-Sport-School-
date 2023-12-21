package kg.nail.automatedbusinesssystemforasportsschool.service;

import kg.nail.automatedbusinesssystemforasportsschool.web.dto.AthleteDTO;
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.CreateGroupDTO;
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.GroupDTO;
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.TrainerDTO;

import java.util.List;

public interface GroupService {

    GroupDTO getById(Long id);

    Long createGroup(CreateGroupDTO group);

    void deleteGroupById(Long id);

    Long updateGroup(Long groupId, GroupDTO groupDTO);

    GroupDTO restoreGroupById(Long groupId);

    List<AthleteDTO> getAthletesByGroupId(Long groupId);

    TrainerDTO getTrainerByGroupId(Long groupId);
}
