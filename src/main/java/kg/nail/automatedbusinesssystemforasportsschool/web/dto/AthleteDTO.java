package kg.nail.automatedbusinesssystemforasportsschool.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Schema(description = "New athlete register")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AthleteDTO {

    Long id;

    @Schema(description = "Имя", example = "Иван")
    @NotNull(message = "Имя должно быть заполнено")
    String firstName;

    @Schema(description = "Фамилия", example = "Иванов")
    @NotNull(message = "Фамилия должна быть заполнена")
    String lastName;

    @Schema(description = "Отчество", example = "Иванович")
    String patronymic;

    @Schema(description = "Имя пользователя", example = "IvanovIvan2000")
    @NotNull(message = "Имя пользователя должно быть заполнено!")
    String username;

    @Schema(description = "Пароль", example = "12345678")
    @NotNull(message = "Пароль не может быть пустым и состоять из менее 8 знаков")
    String password;

    @Schema(description = "Группа спортсмена", example = "Младшая(утренняя)")
    @NotNull(message = "Не выбрана группа спортсмена!")
    Long groupId;

    @Schema(description = "Дата рождения", example = "2022-12-06")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Дата рождения должна быть заполнена!")
    LocalDate dateOfBirth;

    @Schema(description = "Номер телефона", example = "996777566962")
    @NotNull(message = "Номер телефона не должен быть пустым!")
    String phoneNumber;

    @Schema(description = "Домашний адресс спортсмена", example = "ул.Советская 30")
    String address;

    @Schema(description = "Рост", example = "155")
    @NotNull(message = "Рост не должен быть пустым")
    Integer height;

    @Schema(description = "Вес", example = "50")
    @NotNull(message = "Вес не должен быть пустым")
    Double weight;

    @Schema(description = "Имя родителя", example = "Иван")
    @NotNull(message = "Имя родителя не может быть пустым!")
    String parentsFirstName;

    @Schema(description = "Фамилия родителя", example = "Иванович")
    @NotNull(message = "Фамилия родителя не может быть пустой!")
    String parentsLastName;

    @Schema(description = "Номер телефона родителя", example = "996777566962")
    @NotNull(message = "Номер родителя не может быть пустым")
    String parentsPhoneNumber;

    @Schema(description = "Откуда узнали о клубе", example = "Соцсети")
    @NotNull(message = "Источник не должен быть пустым")
    String source;

    @Schema(description = "Школа в которой обучается спортсмен", example = "№63")
    @NotNull(message = "Школа должна быть заполненной")
    String school;
}
