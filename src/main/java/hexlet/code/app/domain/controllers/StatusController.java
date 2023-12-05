package hexlet.code.app.domain.controllers;

import hexlet.code.app.domain.dtos.StatusResponse;
import hexlet.code.app.domain.dtos.StatusRequest;
import hexlet.code.app.domain.service.StatusService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        long totalCount = statusService.count();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(totalCount));

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
