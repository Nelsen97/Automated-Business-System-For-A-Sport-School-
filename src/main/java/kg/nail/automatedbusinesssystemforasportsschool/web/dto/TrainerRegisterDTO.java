package kg.nail.automatedbusinesssystemforasportsschool.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import kg.nail.automatedbusinesssystemforasportsschool.enums.Source;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Schema(description = "Trainer registration DTO")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class TrainerRegisterDTO {

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

    @Schema(description = "Дата рождения", example = "2000-30-12")
    @NotNull(message = "Дата рождения должна быть заполнена!")
    @Past(message = "Дата рождения должна быть в прошлом")
    LocalDate dateOfBirth;

    @Schema(description = "Номер телефона", example = "996777566962")
    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\(?\\d{1,4}\\)?[-.\\s]?\\d{1,9}[-.\\s]?\\d{1,9}$")
    String phoneNumber;

    @Schema(description = "Домашний адресс спортсмена", example = "ул.Советская 30")
    String address;

    @Schema(description = "Откуда узнали о клубе", example = "SOCIAL_NETWORK")
    @NotNull(message = "Источник не должен быть пустым")
    Source source;
}
