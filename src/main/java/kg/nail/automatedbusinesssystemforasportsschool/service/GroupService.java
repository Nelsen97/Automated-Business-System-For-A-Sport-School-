package kg.nail.automatedbusinesssystemforasportsschool.service;

import kg.nail.automatedbusinesssystemforasportsschool.web.dto.GroupDTO;

public interface GroupService {

    GroupDTO getById(Long id);

    GroupDTO createGroup(GroupDTO group);

    void deleteGroupById(Long id);
}
