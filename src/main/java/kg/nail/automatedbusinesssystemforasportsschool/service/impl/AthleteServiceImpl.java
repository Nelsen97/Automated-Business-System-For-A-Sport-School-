package kg.nail.automatedbusinesssystemforasportsschool.service.impl;

import kg.nail.automatedbusinesssystemforasportsschool.entity.Athlete;
import kg.nail.automatedbusinesssystemforasportsschool.entity.Group;
import kg.nail.automatedbusinesssystemforasportsschool.enums.Role;
import kg.nail.automatedbusinesssystemforasportsschool.exception.CustomException;
import kg.nail.automatedbusinesssystemforasportsschool.exception.ResourceNotFoundException;
import kg.nail.automatedbusinesssystemforasportsschool.mappers.AthleteMapper;
import kg.nail.automatedbusinesssystemforasportsschool.repository.AthleteRepository;
import kg.nail.automatedbusinesssystemforasportsschool.repository.GroupRepository;
import kg.nail.automatedbusinesssystemforasportsschool.service.AthleteService;
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.AthleteDTO;
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.TrainerRegisterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AthleteServiceImpl implements AthleteService {

    private final AthleteRepository athleteRepository;
    private final AthleteMapper athleteMapper;
    private final PasswordEncoder encoder;
    private final GroupRepository groupRepository;

    @Override
    public AthleteDTO getUserById(Long athleteId) {
        Athlete athlete = getAthleteById(athleteId);

        return athleteMapper.toDTO(athlete);
    }

    @Override
    public AthleteDTO getByFirstOrLastName(String firstName, String lastName) {

        Athlete athlete = athleteRepository.findByFirstNameContainsIgnoreCaseOrLastNameContainsIgnoreCase(
                firstName, lastName).orElseThrow(
                () -> new ResourceNotFoundException("There is no such athlete!")
        );

        return athleteMapper.toDTO(athlete);
    }

    @Override
    public AthleteDTO registerAthlete(AthleteDTO athleteDTO) {

        if (athleteRepository.existsByUsername(athleteDTO.getUsername())) {
            throw new CustomException("Такой пользователь уже зарегистрирован. " +
                    "Пожалуйста используйте другое имя пользователя.");
        }

        athleteDTO.setPassword(encoder.encode(athleteDTO.getPassword()));

        Group group = groupRepository.findById(athleteDTO.getGroupId()).orElseThrow(
                () -> new ResourceNotFoundException("No such group exists!")
        );

        Athlete athlete = athleteMapper.toEntityFromAthleteDTO(athleteDTO);
        athlete.setGroup(group);
        athlete.setRole(Role.ROLE_ATHLETE);
        athlete.setEnrollmentDate(LocalDate.now());
        athleteRepository.save(athlete);

        return athleteMapper.toDTO(athlete);
    }

    @Override
    public AthleteDTO update(Long athleteId, AthleteDTO athleteDTO) {

        Athlete athlete = getAthleteById(athleteId);
        athleteMapper.updateAthleteFromDTO(athleteDTO, athlete);
        athleteRepository.save(athlete);

        return athleteMapper.toDTO(athlete);
    }

    @Override
    public void delete(Long athleteId) {
        Athlete athlete = getAthleteById(athleteId);
        athlete.setActive(false);
        athleteRepository.save(athlete);
    }

    @Override
    public Athlete getAthleteById(Long athleteId) {
        return athleteRepository.findById(athleteId).orElseThrow(
                () -> new CustomException("Нет такого спортсмена")
        );
    }

    @Override
    @Transactional(readOnly = true)
    public AthleteDTO getByUsername(String username) {
        return athleteMapper.toDTO(athleteRepository.findByUsername(username).orElseThrow(
                () -> new ResourceNotFoundException("User not found.")
        ));
    }

    @Override
    public TrainerRegisterDTO registerTrainer(TrainerRegisterDTO trainer) {

        if (athleteRepository.existsByUsername(trainer.getUsername())) {
            throw new CustomException("Такой пользователь уже зарегистрирован. " +
                    "Пожалуйста используйте другое имя пользователя.");
        }

        trainer.setPassword(encoder.encode(trainer.getPassword()));
        Athlete athlete = athleteMapper.toEntityFromTrainerRegisterDTO(trainer);
        athlete.setActive(true);
        athlete.setRole(Role.ROLE_TRAINER);
        athlete.setEnrollmentDate(LocalDate.now());

        athleteRepository.save(athlete);

        return trainer;
    }


}
