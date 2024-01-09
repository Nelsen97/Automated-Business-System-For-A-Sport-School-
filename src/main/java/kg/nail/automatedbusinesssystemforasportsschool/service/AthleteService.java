package kg.nail.automatedbusinesssystemforasportsschool.service;

import kg.nail.automatedbusinesssystemforasportsschool.entity.Athlete;
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.AthleteDTO;
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.TrainerRegisterDTO;

public interface AthleteService {

    AthleteDTO getUserById(Long athleteId);

    AthleteDTO getByUsername(String username);

    AthleteDTO registerAthlete(AthleteDTO athlete);

    AthleteDTO update(Long id, AthleteDTO athleteDTO);

    void delete(Long athleteId);

    AthleteDTO restoreAthlete(Long athleteId);

    Athlete getAthleteById(Long athleteId);

    TrainerRegisterDTO registerTrainer(TrainerRegisterDTO trainer);
}
