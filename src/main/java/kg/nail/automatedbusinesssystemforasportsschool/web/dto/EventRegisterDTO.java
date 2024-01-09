package kg.nail.automatedbusinesssystemforasportsschool.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Schema(description = "Register event DTO")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventRegisterDTO {

    @Schema(description = "Группа", example = "Младшая (утро)")
    @NotNull(message = "Не выбрана группа спортсмена!")
    @Min(value = 1)
    Long groupId;

    @Schema(description = "Дата тренировки", example = "2024-01-01T09:00:00")
    @NotNull(message = "Дата тренировки должна быть заполнена!")
    LocalDateTime eventDateTime;
}
