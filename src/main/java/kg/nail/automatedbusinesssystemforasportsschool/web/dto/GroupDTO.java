package kg.nail.automatedbusinesssystemforasportsschool.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Schema(description = "Group DTO")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GroupDTO {

    @Schema(description = "Название группы", example = "Младшая (утро)")
    @NotBlank(message = "Название группы не может быть пустой!")
    String name;

    @Schema(description = "Тренер группы", example = "Артём")
    @NotNull
    Long trainerId;

    List<Long> athletes;
}
