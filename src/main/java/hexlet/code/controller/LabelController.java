package hexlet.code.controller;

import hexlet.code.dto.LabelRequest;
import hexlet.code.dto.LabelResponse;
import hexlet.code.service.LabelService;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/labels")
@RequiredArgsConstructor
public class LabelController {

    private final LabelService labelService;

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LabelResponse updateById(@PathVariable Long id, @RequestBody LabelRequest labelRequest) {
        return labelService.updateById(id, labelRequest);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LabelResponse save(@RequestBody LabelRequest labelRequest) {
        return labelService.save(labelRequest);
    }

    @GetMapping("/{id}")
    public LabelResponse findById(@PathVariable Long id) {
        return labelService.findById(id);
    }

    @GetMapping
    public ResponseEntity<List<LabelResponse>> findAll() {
        List<LabelResponse> labels = labelService.findAll();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(labels.size()));

        return new ResponseEntity<>(labels, headers, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        labelService.deleteById(id);
    }
}
