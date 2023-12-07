package kg.nail.automatedbusinesssystemforasportsschool.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import kg.nail.automatedbusinesssystemforasportsschool.service.AthleteService;
import kg.nail.automatedbusinesssystemforasportsschool.service.AuthService;
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.AthleteExampleDTO;
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.auth.JwtRequest;
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.auth.JwtResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Auth Controller", description = "Auth API")
public class AuthController {

    private final AuthService authService;
    private final AthleteService athleteService;

    @PostMapping("/login")
    public JwtResponse login(@RequestBody JwtRequest loginRequest) {

        return authService.login(loginRequest);
    }

    @PostMapping("/register")
    public AthleteExampleDTO register(@RequestBody AthleteExampleDTO userDTO) {

        return athleteService.createAthlete(userDTO);
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(@RequestBody String refreshToken) {

        return authService.refresh(refreshToken);
    }
}
