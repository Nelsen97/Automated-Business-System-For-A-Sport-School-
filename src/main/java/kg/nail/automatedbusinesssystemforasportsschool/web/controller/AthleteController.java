package kg.nail.automatedbusinesssystemforasportsschool.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kg.nail.automatedbusinesssystemforasportsschool.service.AthleteService;
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.AthleteDTO;
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.TrainerRegisterDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/athlete")
@RequiredArgsConstructor
@Tag(name = "Athlete API", description = "API атлета")
@Slf4j
public class AthleteController {

    private final AthleteService athleteService;

    @PostMapping("/register/athlete")
    public ResponseEntity<AthleteDTO> registerAthlete(@Valid @RequestBody AthleteDTO athlete) {
        return new ResponseEntity<>(athleteService.registerAthlete(athlete), HttpStatus.OK);
    }

    @PostMapping("/register/trainer")
    public ResponseEntity<TrainerRegisterDTO> registerTrainer(@Valid @RequestBody TrainerRegisterDTO trainer) {
        return new ResponseEntity<>(athleteService.registerTrainer(trainer), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AthleteDTO> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(athleteService.getUserById(id), HttpStatus.OK);
    }

    @PutMapping("/update/athlete/{id}")
    public ResponseEntity<AthleteDTO> updateAthlete(@PathVariable Long id, @RequestBody AthleteDTO athlete) {
        return new ResponseEntity<>(athleteService.update(id, athlete), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteAthlete(@PathVariable Long id) {
        athleteService.delete(id);

        return ResponseEntity.ok().build();
    }
}
