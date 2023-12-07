package kg.nail.automatedbusinesssystemforasportsschool.service.impl;

import kg.nail.automatedbusinesssystemforasportsschool.entity.Group;
import kg.nail.automatedbusinesssystemforasportsschool.exception.ResourceNotFoundException;
import kg.nail.automatedbusinesssystemforasportsschool.mappers.GroupMapper;
import kg.nail.automatedbusinesssystemforasportsschool.repository.GroupRepository;
import kg.nail.automatedbusinesssystemforasportsschool.service.GroupService;
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.GroupDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;
    @Override
    public GroupDTO getById(Long id) {
        Group group = groupRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Такой группы не существует."));

        return groupMapper.toDTO(group);
    }

    @Override
    public GroupDTO createGroup(GroupDTO group) {
        return null;
    }

    @Override
    public void deleteGroupById(Long id) {

    }
}
