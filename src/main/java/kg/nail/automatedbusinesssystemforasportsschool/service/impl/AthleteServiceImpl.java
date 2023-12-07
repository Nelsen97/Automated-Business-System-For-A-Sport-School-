package kg.nail.automatedbusinesssystemforasportsschool.service.impl;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
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
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.AthleteExampleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AthleteServiceImpl implements AthleteService {

    private final AthleteRepository athleteRepository;
    private final AthleteMapper athleteMapper;
    private final PasswordEncoder encoder;
    private final GroupRepository groupRepository;

    @Override
    public AthleteExampleDTO createAthlete(AthleteExampleDTO athleteDTO) {

        if (athleteRepository.existsByUsername(athleteDTO.getUsername())) {
            throw new IllegalStateException("User already exists.");
        }

        Athlete athlete = new Athlete();

        athlete.setUsername(athleteDTO.getUsername());
        athlete.setPassword(encoder.encode(athleteDTO.getPassword()));
        athlete.setRoles(Set.of(Role.ROLE_ATHLETE));
        athlete.setActive(true);
        athleteRepository.save(athlete);
        return athleteMapper.toExampleDTO(athlete);
    }

    @Override
    public AthleteDTO registerAthlete(AthleteDTO athleteDTO) {

        Athlete athlete = new Athlete();
        athlete.setPassword(encoder.encode(athleteDTO.getPassword()));

        validateRegistrationData(athleteDTO);


        athlete.setUsername(athleteDTO.getUsername());
        athlete.setFirstName(athleteDTO.getFirstName());
        athlete.setLastName(athleteDTO.getLastName());
        athlete.setPatronymic(athleteDTO.getPatronymic());
        athlete.setDateOfBirth(athleteDTO.getDateOfBirth());
        athlete.setAddress(athleteDTO.getAddress());
        athlete.setEnrollmentDate(LocalDate.now());
        athlete.setRoles(Set.of(Role.ROLE_ATHLETE));
        athlete.setPhoneNumber(athleteDTO.getPhoneNumber());
        athlete.setParentsPhoneNumber(athleteDTO.getParentsPhoneNumber());
        Group group = groupRepository.findById(athleteDTO.getGroupId()).orElseThrow(
                () -> new ResourceNotFoundException("Такой группы не существует!")
        );

        athlete.setGroup(group);
        athlete.setParentsLastName(athleteDTO.getParentsLastName());
        athlete.setParentsFirstName(athleteDTO.getParentsFirstName());
        athlete.setSource(athleteDTO.getSource());
        athlete.setSchool(athleteDTO.getSchool());
        athlete.setHeight(athleteDTO.getHeight());
        athlete.setWeight(athleteDTO.getWeight());
        athlete.setActive(true);

        athleteRepository.save(athlete);

        return athleteMapper.toDTO(athlete);
    }

    private void validateRegistrationData(AthleteDTO athleteDTO) {

        if (athleteRepository.existsByUsername(athleteDTO.getUsername())) {
            throw new IllegalStateException("Спортсмен с никнейм - %s уже существует".formatted(athleteDTO.getUsername()));
        }

        athleteDTO.setPhoneNumber(checkPhoneNumber(athleteDTO.getPhoneNumber()));
        athleteDTO.setParentsPhoneNumber(checkPhoneNumber(athleteDTO.getParentsPhoneNumber()));

        if (athleteDTO.getDateOfBirth() == null || athleteDTO.getDateOfBirth().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Неверная дата рождения.");
        }
    }

    private String checkPhoneNumber(String phoneNumber) {

        if (phoneNumber != null) {
            try {
                PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

                Phonenumber.PhoneNumber parsedPhoneNumber = phoneNumberUtil.parse(phoneNumber, null);

                int countryCode = parsedPhoneNumber.getCountryCode();

                if (countryCode == 996) {
                    return phoneNumberUtil.format(parsedPhoneNumber, PhoneNumberUtil.PhoneNumberFormat.NATIONAL);
                } else {
                    return phoneNumberUtil.format(parsedPhoneNumber, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL);
                }

            } catch (NumberParseException e) {
                throw new CustomException("Неверный формат номера телефона!");
            }
        } else {
            throw new ResourceNotFoundException("Номер телефона не должен быть пустым!");
        }
    }

    @Override
    public AthleteExampleDTO getById(Long id) {
        Athlete athlete = athleteRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Athlete not found")
        );
        return athleteMapper.toExampleDTO(athlete);
    }

    @Override
    public AthleteExampleDTO getByName(String name) {
        return null;
    }

    @Override
    public AthleteExampleDTO update(AthleteExampleDTO athlete) {
        return null;
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
    @Transactional(readOnly = true)
    public AthleteExampleDTO getByUsername(String username) {
        return athleteMapper.toExampleDTO(athleteRepository.findByUsername(username).orElseThrow(
                () -> new ResourceNotFoundException("User not found.")
        ));
    }
}
