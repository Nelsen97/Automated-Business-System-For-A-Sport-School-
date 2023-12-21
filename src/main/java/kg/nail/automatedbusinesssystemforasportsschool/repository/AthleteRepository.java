package kg.nail.automatedbusinesssystemforasportsschool.repository;

import kg.nail.automatedbusinesssystemforasportsschool.entity.Athlete;
import kg.nail.automatedbusinesssystemforasportsschool.entity.Group;
import kg.nail.automatedbusinesssystemforasportsschool.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AthleteRepository extends JpaRepository<Athlete, Long> {
    Optional<Athlete> findByUsername(String username);
    Optional<Athlete> findByFirstNameContainsIgnoreCaseOrLastNameContainsIgnoreCase(String firstName, String lastName);

    Optional<Athlete> findByRoleAndId(Role role, Long trainerId);

    List<Athlete> findAllByGroup(Group group);

    boolean existsByUsername(String username);
}
