package kg.nail.automatedbusinesssystemforasportsschool.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import kg.nail.automatedbusinesssystemforasportsschool.entity.Parent;
import kg.nail.automatedbusinesssystemforasportsschool.enums.Role;
import kg.nail.automatedbusinesssystemforasportsschool.enums.Source;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@Schema(description = "New athlete register")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class AthleteDTO {

    Long id;

    @Schema(description = "Имя", example = "Иван")
    @NotBlank(message = "Имя должно быть заполнено")
    String firstName;

    @Schema(description = "Фамилия", example = "Иванов")
    @NotBlank(message = "Фамилия должна быть заполнена")
    String lastName;

    @Schema(description = "Отчество", example = "Иванович")
    String patronymic;

    @Schema(description = "Имя пользователя", example = "IvanovIvan")
    @NotBlank(message = "Имя пользователя должно быть заполнено!")
    @Size(min = 6, max = 50, message = "Имя пользователя должно содержать от 6 до 50 символов")
    String username;

    @Schema(description = "Пароль", example = "Password123")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).{8,}$",
            message = "Пароль должен содержать минимум 8 знаков, включая хотя бы одну большую букву и одну цифру")
    String password;

    @Schema(description = "Группа спортсмена", example = "Младшая (утро)")
    @NotNull(message = "Не выбрана группа спортсмена!")
    @Min(value = 1)
    Long groupId;

    @Schema(description = "Дата зачисления")
    @NotNull(message = "Дата зачисления должна быть заполнена!")
    private final LocalDate enrollmentDate = LocalDate.now();

    @Schema(description = "Дата рождения", example = "2015-07-08")
    @NotNull(message = "Дата рождения должна быть заполнена!")
    @Past(message = "Дата рождения должна быть в прошлом")
    LocalDate dateOfBirth;

    @Schema(description = "Номер телефона", example = "996777566962")
    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\(?\\d{1,4}\\)?[-.\\s]?\\d{1,9}[-.\\s]?\\d{1,9}$")
    String phoneNumber;

    @Schema(description = "Домашний адресс спортсмена", example = "ул.Советская 30")
    String address;

    @Schema(description = "Рост", example = "155")
    @NotNull(message = "Рост не должен быть пустым")
    @Min(value = 1, message = "Рост не может быть меньше 1")
    Integer height;

    @Schema(description = "Вес", example = "50")
    @NotNull(message = "Вес не должен быть пустым")
    @Min(value = 1, message = "Вес не может быть меньше 1")
    Double weight;

    @Schema(description = "Родители спортсмена")
    List<Parent> parents;

    @Schema(description = "Откуда узнали о клубе", example = "SOCIAL_NETWORK")
    @NotNull(message = "Источник не должен быть пустым")
    Source source;

    @Schema(description = "Школа в которой обучается спортсмен", example = "№63")
    @NotBlank(message = "Школа должна быть заполненной")
    String school;

    @Schema(description = "Роль в школе", example = "ROLE_ATHLETE")
    final Role role = Role.ROLE_ATHLETE;

    @Schema(description = "Активен ли атлет", example = "true")
    final boolean active = true;
}
