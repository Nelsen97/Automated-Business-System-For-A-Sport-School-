package kg.nail.automatedbusinesssystemforasportsschool.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "athletes")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Athlete extends User {

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
