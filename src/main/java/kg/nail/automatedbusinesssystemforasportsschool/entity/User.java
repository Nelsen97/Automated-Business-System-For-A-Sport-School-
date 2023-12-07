package kg.nail.automatedbusinesssystemforasportsschool.entity;

import jakarta.persistence.*;
import kg.nail.automatedbusinesssystemforasportsschool.enums.Role;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

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

    @Column(name = "personal_phone_number")
    String phoneNumber;

    String address;

    @Column(name = "enrollment_date")
    LocalDate enrollmentDate;

    String username;

    String password;

    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "users_roles")
    @Enumerated(value = EnumType.STRING)
    Set<Role> roles;
}
