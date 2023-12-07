package kg.nail.automatedbusinesssystemforasportsschool.service;

import kg.nail.automatedbusinesssystemforasportsschool.web.dto.AthleteDTO;
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.AthleteExampleDTO;

public interface AthleteService {

    AthleteExampleDTO getById(Long id);

    AthleteExampleDTO getByUsername(String username);

    AthleteExampleDTO createAthlete(AthleteExampleDTO athlete);

    AthleteDTO registerAthlete(AthleteDTO athlete);

    AthleteExampleDTO getByName(String name);

    AthleteExampleDTO update(AthleteExampleDTO athlete);

    void delete(Long id);

    boolean isAthleteActive(Long athleteId);
}
