package kg.nail.automatedbusinesssystemforasportsschool.security;

import kg.nail.automatedbusinesssystemforasportsschool.service.AthleteService;
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.AthleteExampleDTO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtUserDetailsService implements UserDetailsService {
    AthleteService athleteService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AthleteExampleDTO athlete = athleteService.getByUsername(username);
        return JwtEntityFactory.create(athlete);
    }
}
