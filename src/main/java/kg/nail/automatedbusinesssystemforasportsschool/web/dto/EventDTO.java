package kg.nail.automatedbusinesssystemforasportsschool.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Schema(description = "Event DTO")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventDTO {

    Long id;

    @Schema(description = "Группа", example = "Младшая (утро)")
    @NotNull(message = "Не выбрана группа спортсмена!")
    @Min(value = 1)
    Long groupId;

    @Schema(description = "Дата тренировки", example = "2024-01-01T09:00:00")
    @NotNull(message = "Дата рождения должна быть заполнена!")
    @Past(message = "Дата рождения должна быть в прошлом")
    LocalDateTime eventDateTime;
}
