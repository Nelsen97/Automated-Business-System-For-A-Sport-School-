package kg.nail.automatedbusinesssystemforasportsschool.service.impl;

import kg.nail.automatedbusinesssystemforasportsschool.security.JwtTokenProvider;
import kg.nail.automatedbusinesssystemforasportsschool.service.AthleteService;
import kg.nail.automatedbusinesssystemforasportsschool.service.AuthService;
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.AthleteDTO;
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.auth.JwtRequest;
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.auth.JwtResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AthleteService athleteService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public JwtResponse login(JwtRequest loginRequest) {

        JwtResponse jwtResponse = new JwtResponse();

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername().toLowerCase(), loginRequest.getPassword())
        );

        AthleteDTO user = athleteService.getByUsername(loginRequest.getUsername());

        jwtResponse.setId(user.getId());
        jwtResponse.setUsername(user.getUsername());
        jwtResponse.setAccessToken(jwtTokenProvider.createAccessToken(
                user.getId(), user.getUsername(),user.getRole()
        ));

        jwtResponse.setRefreshToken(jwtTokenProvider.createRefreshToken(user.getId(), user.getUsername()));

        return jwtResponse;
    }

    @Override
    public JwtResponse refresh(String refreshToken) {

        return jwtTokenProvider.refreshUserTokens(refreshToken);
    }
}
