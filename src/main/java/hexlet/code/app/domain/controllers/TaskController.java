package hexlet.code.app.domain.controllers;

import hexlet.code.app.domain.dtos.TaskRequest;
import hexlet.code.app.domain.dtos.TaskResponse;
import hexlet.code.app.domain.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PutMapping("/{id}")
    public TaskResponse updateById(@PathVariable Long id, @RequestBody TaskRequest taskRequest) {
        return taskService.updateById(id, taskRequest);
    }

    @PostMapping
    public TaskResponse save(@RequestBody TaskRequest taskRequest) {
        return taskService.save(taskRequest);
    }

    @GetMapping("/{id}")
    public TaskResponse findById(@PathVariable Long id) {
        return taskService.findById(id);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> findAll() {
        List<TaskResponse> tasks = taskService.findAll();
        long totalCount = taskService.count();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(totalCount));

        return new ResponseEntity<>(tasks, headers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        taskService.deleteById(id);
    }
}

