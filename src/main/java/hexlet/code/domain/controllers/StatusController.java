package hexlet.code.domain.controllers;

import hexlet.code.domain.dtos.StatusResponse;
import hexlet.code.domain.dtos.StatusRequest;
import hexlet.code.domain.service.StatusService;
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
public class StatusController {

    private final StatusService statusService;

    @GetMapping("/{id}")
    public StatusResponse findById(@PathVariable Long id) {
        return statusService.findById(id);
    }

    @GetMapping
    public ResponseEntity<List<StatusResponse>> findAll() {
        List<StatusResponse> statuses = statusService.findAll();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(statuses.size()));

        return new ResponseEntity<>(statuses, headers, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public StatusResponse updateById(@PathVariable Long id, @RequestBody @Valid StatusRequest statusRequest) {
        return statusService.updateById(id, statusRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        statusService.deleteById(id);
    }

    @PostMapping
    public StatusResponse save(@RequestBody @Valid StatusRequest statusRequest) {
        return statusService.save(statusRequest);
    }
}
