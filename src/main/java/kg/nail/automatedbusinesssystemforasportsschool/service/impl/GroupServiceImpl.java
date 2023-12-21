package kg.nail.automatedbusinesssystemforasportsschool.service.impl;

import kg.nail.automatedbusinesssystemforasportsschool.entity.Athlete;
import kg.nail.automatedbusinesssystemforasportsschool.entity.Group;
import kg.nail.automatedbusinesssystemforasportsschool.enums.Role;
import kg.nail.automatedbusinesssystemforasportsschool.exception.CustomException;
import kg.nail.automatedbusinesssystemforasportsschool.exception.ResourceNotFoundException;
import kg.nail.automatedbusinesssystemforasportsschool.mappers.AthleteMapper;
import kg.nail.automatedbusinesssystemforasportsschool.mappers.GroupMapper;
import kg.nail.automatedbusinesssystemforasportsschool.repository.AthleteRepository;
import kg.nail.automatedbusinesssystemforasportsschool.repository.GroupRepository;
import kg.nail.automatedbusinesssystemforasportsschool.service.AthleteService;
import kg.nail.automatedbusinesssystemforasportsschool.service.GroupService;
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.AthleteDTO;
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.CreateGroupDTO;
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.GroupDTO;
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.TrainerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;
    private final AthleteService athleteService;
    private final AthleteMapper athleteMapper;
    private final AthleteRepository athleteRepository;

    @Override
    public GroupDTO getById(Long id) {
        Group group = groupRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Такой группы не существует."));

        return groupMapper.toGroupDTO(group);
    }

    @Override
    public Long createGroup(CreateGroupDTO groupDTO) {

        if (groupRepository.existsByName(groupDTO.getName())) {
            throw new CustomException("Такая группа уже существует");
        }
        if (athleteRepository.findByRoleAndId(Role.ROLE_TRAINER, groupDTO.getTrainerId()).isEmpty()) {
            throw new CustomException("Такого тренера не существует!");
        }

        Group group = groupMapper.createDTOToEntity(groupDTO);
        group.setActive(true);
        groupRepository.save(group);

        return group.getId();
    }

    @Override
    public void deleteGroupById(Long id) {
        Group group = getGroupById(id);
        group.setActive(false);
        groupRepository.save(group);
    }

    @Override
    public Long updateGroup(Long groupId, GroupDTO groupDTO) {
        Group group = getGroupById(groupId);
        Athlete trainer = athleteService.getAthleteById(groupDTO.getTrainerId());

        if (group.getName().equals(groupDTO.getName())) {
            group.setName(groupDTO.getName());
        } else if (groupRepository.existsByName(groupDTO.getName())) {
            throw new CustomException("Группа с таким именем уже существует");
        } else {
            group.setName(groupDTO.getName());
        }
        group.setTrainer(trainer);

        groupRepository.save(group);

        return group.getId();
    }

    @Override
    public GroupDTO restoreGroupById(Long groupId) {
        Group group = getGroupById(groupId);
        if (Boolean.FALSE.equals(group.getActive())) {
            group.setActive(true);
            groupRepository.save(group);
        } else {
            throw new CustomException("Группа итак активна!");
        }

        return groupMapper.toGroupDTO(group);
    }

    @Override
    public List<AthleteDTO> getAthletesByGroupId(Long groupId) {
        Group group = getGroupById(groupId);
        List<Athlete> athletesByGroup = athleteRepository.findAllByGroup(group);


        return athletesByGroup.stream()
                .map(athleteMapper::toDTO)
                .toList();
    }

    @Override
    public TrainerDTO getTrainerByGroupId(Long groupId) {
        Group group = getGroupById(groupId);

        Athlete trainer = athleteRepository.findByRoleAndId(Role.ROLE_TRAINER, group.getTrainer().getId()).orElseThrow(
                () -> new ResourceNotFoundException("Нет такого тренера")
        );

        return athleteMapper.toTrainerDTOFromEntity(trainer);
    }

    private Group getGroupById(Long id) {
        return groupRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("There is no such group")
        );
    }
}
