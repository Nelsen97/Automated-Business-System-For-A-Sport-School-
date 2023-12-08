package kg.nail.automatedbusinesssystemforasportsschool.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Schema(description = "Group DTO")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GroupDTO {

    Long id;

    @Schema(description = "Название группы", example = "Младшая (утро)")
    @NotBlank(message = "Название группы не может быть пустой!")
    String name;
}
