package kg.nail.automatedbusinesssystemforasportsschool.service.impl;

import kg.nail.automatedbusinesssystemforasportsschool.entity.Athlete;
import kg.nail.automatedbusinesssystemforasportsschool.entity.Group;
import kg.nail.automatedbusinesssystemforasportsschool.exception.ResourceNotFoundException;
import kg.nail.automatedbusinesssystemforasportsschool.mappers.AthleteMapper;
import kg.nail.automatedbusinesssystemforasportsschool.repository.AthleteRepository;
import kg.nail.automatedbusinesssystemforasportsschool.repository.GroupRepository;
import kg.nail.automatedbusinesssystemforasportsschool.service.AthleteService;
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.AthleteDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AthleteServiceImpl implements AthleteService {

    private final AthleteRepository athleteRepository;
    private final AthleteMapper athleteMapper;
    private final PasswordEncoder encoder;
    private final GroupRepository groupRepository;

    @Override
    public AthleteDTO registerAthlete(AthleteDTO athleteDTO) {

        athleteDTO.setPassword(encoder.encode(athleteDTO.getPassword()));
        Athlete athlete = athleteMapper.toEntity(athleteDTO);


        Group group = groupRepository.findById(athleteDTO.getGroupId()).orElseThrow(
                () -> new ResourceNotFoundException("No such group exists!")
        );

        athlete.setGroup(group);
        athleteRepository.save(athlete);

        return athleteMapper.toDTO(athlete);
    }

    @Override
    public AthleteDTO getByFirstOrLastName(String firstName, String lastName) {

        Athlete athlete = athleteRepository.findByFirstNameOrLastName(firstName.toLowerCase(),
                lastName.toLowerCase()).orElseThrow(
                () -> new ResourceNotFoundException("There is no such athlete!"));

        return athleteMapper.toDTO(athlete);
    }

    @Override
    public AthleteDTO update(AthleteDTO athleteDTO) {
        Athlete athlete = athleteRepository.save(athleteMapper.toEntity(athleteDTO));

        return athleteMapper.toDTO(athlete);
    }

    @Override
    public void delete(Long id) {
        athleteRepository.deleteById(id);
    }

    @Override
    public boolean isAthleteActive(Long athleteId) {
        return false;
    }

    @Override
    public AthleteDTO getById(Long id) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public AthleteDTO getByUsername(String username) {
        return athleteMapper.toDTO(athleteRepository.findByUsername(username).orElseThrow(
                () -> new ResourceNotFoundException("User not found.")
        ));
    }
}
