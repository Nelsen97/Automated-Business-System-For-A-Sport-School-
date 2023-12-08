package kg.nail.automatedbusinesssystemforasportsschool.service;

import kg.nail.automatedbusinesssystemforasportsschool.web.dto.AthleteDTO;

public interface AthleteService {

    AthleteDTO getById(Long id);

    AthleteDTO getByUsername(String username);

    AthleteDTO registerAthlete(AthleteDTO athlete);

    AthleteDTO getByFirstOrLastName(String firstName, String lastName);

    AthleteDTO update(AthleteDTO athlete);

    void delete(Long id);

    boolean isAthleteActive(Long athleteId);
}
