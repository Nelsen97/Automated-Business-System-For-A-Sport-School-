package kg.nail.automatedbusinesssystemforasportsschool.security;

import kg.nail.automatedbusinesssystemforasportsschool.enums.Role;
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.AthleteExampleDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JwtEntityFactory {

    public static JwtEntity create(AthleteExampleDTO athlete) {
        return new JwtEntity(
                athlete.getId(),
                athlete.getUsername(),
                athlete.getPassword(),
                mapToGrantedAuthorities(new ArrayList<>(athlete.getRoles()))
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> roles) {
        return roles.stream()
                .map(Enum::name)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
