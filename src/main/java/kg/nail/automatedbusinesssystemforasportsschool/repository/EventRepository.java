package kg.nail.automatedbusinesssystemforasportsschool.repository;

import kg.nail.automatedbusinesssystemforasportsschool.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByGroupId(Long groupId);
}
