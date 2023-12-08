package kg.nail.automatedbusinesssystemforasportsschool.security;

import kg.nail.automatedbusinesssystemforasportsschool.enums.Role;
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.AthleteDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;

public class JwtEntityFactory {

    public static JwtEntity create(AthleteDTO athlete) {
        return new JwtEntity(
                athlete.getId(),
                athlete.getUsername(),
                athlete.getPassword(),
                mapToGrantedAuthorities(athlete.getRole())
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(Role role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
    }
}

