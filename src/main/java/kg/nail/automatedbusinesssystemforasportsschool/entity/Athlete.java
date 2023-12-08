package kg.nail.automatedbusinesssystemforasportsschool.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import kg.nail.automatedbusinesssystemforasportsschool.enums.Role;
import kg.nail.automatedbusinesssystemforasportsschool.enums.Source;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "athletes")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Athlete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    String patronymic;

    @Column(name = "date_of_birth")
    @JsonFormat(pattern = "dd-MM-yyyy")
    LocalDate dateOfBirth;

    @Column(name = "phone_number")
    String phoneNumber;

    String address;

    @Column(name = "enrollment_date")
    @JsonFormat(pattern = "dd-MM-yyyy")
    LocalDate enrollmentDate;

    @Enumerated(value = EnumType.STRING)
    Source source;

    String username;

    String password;

    Boolean active;

    @Enumerated(value = EnumType.STRING)
    Role role;

    @ManyToOne
    @JoinColumn(name = "group_id")
    Group group;

    @Column(name = "payment_amount")
    BigDecimal paymentAmount;

    String school;

    Integer height;

    Double weight;

    @ManyToMany(mappedBy = "children")
    List<Parent> parents;
}
