package kg.nail.automatedbusinesssystemforasportsschool.repository;

import kg.nail.automatedbusinesssystemforasportsschool.entity.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AthleteRepository extends JpaRepository<Athlete, Long> {
    Optional<Athlete> findByUsername(String username);
    Optional<Athlete> findByFirstNameOrLastName(String firstName, String lastName);

    boolean existsByUsername(String username);
}
