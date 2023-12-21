package kg.nail.automatedbusinesssystemforasportsschool.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Schema(description = "Add athletes to group")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddedAthletesToGroupDTO {

    Long id;

    List<Long> athletes;
}
