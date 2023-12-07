package kg.nail.automatedbusinesssystemforasportsschool.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kg.nail.automatedbusinesssystemforasportsschool.enums.Role;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Schema(description = "Athlete example DTO")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AthleteExampleDTO {
    @Schema(description = "User id", example = "1")
    Long id;
    String username;
    String password;
    Boolean active;
    private Set<Role> roles;
}
