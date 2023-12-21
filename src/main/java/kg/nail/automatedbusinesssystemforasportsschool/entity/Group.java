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
@Table(name = "groups")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    Athlete trainer;

    @Builder.Default
    Boolean active = true;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    List<Athlete> athletes;
}
