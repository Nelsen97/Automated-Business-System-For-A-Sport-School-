package kg.nail.automatedbusinesssystemforasportsschool.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Schema(description = "Group DTO")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ParentDTO {

    Long id;

    @Schema(description = "Имя", example = "Иван")
    @NotNull(message = "Имя должно быть заполнено")
    String firstName;

    @Schema(description = "Фамилия", example = "Иванов")
    @NotNull(message = "Фамилия должна быть заполнена")
    String lastName;

    @Schema(description = "Отчество", example = "Иванович")
    String patronymic;

    @Schema(description = "Номер телефона", example = "996777566962")
    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\(?\\d{1,4}\\)?[-.\\s]?\\d{1,9}[-.\\s]?\\d{1,9}$")
    String phoneNumber;
}
