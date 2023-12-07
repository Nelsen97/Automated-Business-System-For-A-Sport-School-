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

    @Column(name = "parents_last_name")
    String parentsLastName;

    @Column(name = "parents_first_name")
    String parentsFirstName;

    @Column(name = "parents_phone_number")
    String parentsPhoneNumber;

    String source;

    String school;

    Integer height;

    Double weight;

    @Column(columnDefinition = "boolean default true")
    Boolean active;

    @ManyToMany(mappedBy = "children")
    List<Parent> parents;
}
