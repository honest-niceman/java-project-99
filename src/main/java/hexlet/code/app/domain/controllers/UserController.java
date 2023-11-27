package hexlet.code.app.domain.controllers;

import hexlet.code.app.domain.dtos.UserRequestDto;
import hexlet.code.app.domain.dtos.UserResponseDto;
import hexlet.code.app.domain.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @DeleteMapping("/{id}")
    @PreAuthorize(value = "@userService.findById(#id).getEmail() == authentication.name")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll() {
        List<UserResponseDto> users = userService.findAll();
        long totalCount = userService.count();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(totalCount));

        return new ResponseEntity<>(users, headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public UserResponseDto findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping
    public UserResponseDto save(@RequestBody @Valid UserRequestDto userRequestDto) {
        return userService.save(userRequestDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize(value = "@userService.findById(#id).getEmail() == authentication.name")
    public UserResponseDto updateById(@PathVariable Long id,
                                      @Valid @RequestBody UserRequestDto userRequestDto) {
        return userService.updateById(id, userRequestDto);
    }
}
