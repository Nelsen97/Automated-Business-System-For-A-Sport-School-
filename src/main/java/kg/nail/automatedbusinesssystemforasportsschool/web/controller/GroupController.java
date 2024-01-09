package kg.nail.automatedbusinesssystemforasportsschool.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kg.nail.automatedbusinesssystemforasportsschool.service.GroupService;
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.AthleteDTO;
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.CreateGroupDTO;
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.GroupDTO;
import kg.nail.automatedbusinesssystemforasportsschool.web.dto.TrainerDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/group")
@RequiredArgsConstructor
@Tag(name = "Group API", description = "API группы")
@Slf4j
public class GroupController {

    private final GroupService groupService;

    @GetMapping("/{id}")
    public ResponseEntity<GroupDTO> getGroupById(@PathVariable Long id) {
        return new ResponseEntity<>(groupService.getGroupById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Long> createGroup(@Valid @RequestBody CreateGroupDTO createGroupDTO) {
        return new ResponseEntity<>(groupService.createGroup(createGroupDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        groupService.delete(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Long> updateGroup(@PathVariable("id") Long groupId,
                                            @Valid @RequestBody GroupDTO groupDTO) {
        return new ResponseEntity<>(groupService.update(groupId, groupDTO), HttpStatus.OK);
    }

    @PutMapping("/restore/{id}")
    public ResponseEntity<GroupDTO> restoreGroup(@PathVariable("id") Long groupId) {
        return new ResponseEntity<>(groupService.restoreGroupById(groupId), HttpStatus.OK);
    }

    @GetMapping("/athletes/{id}")
    public ResponseEntity<List<AthleteDTO>> getAthletesByGroupId(@PathVariable("id") Long groupId) {
        return new ResponseEntity<>(groupService.getAthletesByGroupId(groupId), HttpStatus.OK);
    }

    @GetMapping("/trainer/{id}")
    public ResponseEntity<TrainerDTO> getTrainerByGroupId(@PathVariable("id") Long groupId) {
        return new ResponseEntity<>(groupService.getTrainerByGroupId(groupId), HttpStatus.OK);
    }
}
