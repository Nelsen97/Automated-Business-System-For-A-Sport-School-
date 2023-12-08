package kg.nail.automatedbusinesssystemforasportsschool.entity;

import jakarta.persistence.*;
import kg.nail.automatedbusinesssystemforasportsschool.enums.Role;
import kg.nail.automatedbusinesssystemforasportsschool.enums.Source;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@MappedSuperclass
@Getter
@Setter
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    String patronymic;

    @Column(name = "date_of_birth")
    LocalDate dateOfBirth;

    @Column(name = "phone_number")
    String phoneNumber;

    String address;

    @Column(name = "enrollment_date")
    LocalDate enrollmentDate;

    @Enumerated(value = EnumType.STRING)
    Source source;

    String username;

    String password;

    Boolean active;

    @Enumerated(value = EnumType.STRING)
    Role role;
}
