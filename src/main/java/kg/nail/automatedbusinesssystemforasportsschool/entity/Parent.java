package kg.nail.automatedbusinesssystemforasportsschool.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "parents")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    String patronymic;

    @Column(name = "phone_number")
    String phoneNumber;

    @ManyToMany
    @JoinTable(
            name = "parents_children",
            joinColumns = @JoinColumn(name = "parent_id"),
            inverseJoinColumns = @JoinColumn(name = "athlete_id"))
    List<Athlete> children;

}
