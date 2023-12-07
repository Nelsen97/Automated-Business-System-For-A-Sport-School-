package kg.nail.automatedbusinesssystemforasportsschool.entity;

import jakarta.persistence.*;
import kg.nail.automatedbusinesssystemforasportsschool.entity.Athlete;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "groups")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @OneToMany(mappedBy = "group")
    List<Athlete> athletes;

}
