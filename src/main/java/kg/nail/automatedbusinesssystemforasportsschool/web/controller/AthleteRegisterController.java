package kg.nail.automatedbusinesssystemforasportsschool.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import kg.nail.automatedbusinesssystemforasportsschool.exception.ResourceNotFoundException;
import kg.nail.automatedbusinesssystemforasportsschool.service.AthleteService;
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.AthleteDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/athlete")
@RequiredArgsConstructor
@Tag(name = "Регистрация атлета", description = "АПИ регистрации атлета")
@Slf4j
public class AthleteRegisterController {

    private final AthleteService athleteService;

    @PostMapping("/register")
    public ResponseEntity<?> registerAthlete(@RequestBody AthleteDTO athlete) {
        try {
            return new ResponseEntity<>(athleteService.registerAthlete(athlete), HttpStatus.NOT_FOUND);
        } catch (ResourceNotFoundException | IllegalArgumentException | IllegalStateException e) {
            log.error("", e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
