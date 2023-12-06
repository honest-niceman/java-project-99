package hexlet.code.controller;

import hexlet.code.dto.TaskStatusRequest;
import hexlet.code.dto.TaskStatusResponse;
import hexlet.code.service.StatusService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/task_statuses")
@RequiredArgsConstructor
public class TaskStatusController {

    private final StatusService statusService;

    @GetMapping("/{id}")
    public TaskStatusResponse findById(@PathVariable Long id) {
        return statusService.findById(id);
    }

    @GetMapping
    public ResponseEntity<List<TaskStatusResponse>> findAll() {
        List<TaskStatusResponse> statuses = statusService.findAll();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(statuses.size()));

        return new ResponseEntity<>(statuses, headers, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public TaskStatusResponse updateById(@PathVariable Long id,
                                         @RequestBody @Valid TaskStatusRequest taskStatusRequest) {
        return statusService.updateById(id, taskStatusRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        statusService.deleteById(id);
    }

    @PostMapping
    public TaskStatusResponse save(@RequestBody @Valid TaskStatusRequest taskStatusRequest) {
        return statusService.save(taskStatusRequest);
    }
}
