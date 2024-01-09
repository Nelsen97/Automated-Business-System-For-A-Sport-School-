package kg.nail.automatedbusinesssystemforasportsschool.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "events")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "The unique ID of the user", example = "1")
    Long id;

    @ManyToOne
    @JoinColumn(name = "group_id")
    @JsonIgnoreProperties({"trainer", "athletes"})
    @Schema(description = "The unique ID of the user", example = "Младшая (утро)")
    Group group;

    @Column(name = "event_date_time")
    @Schema(description = "Date and time of training", example = "2024-01-01T09:00:00Z")
    LocalDateTime eventDateTime;
}
