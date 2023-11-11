package hexlet.code.app.controllers;

import hexlet.code.app.dtos.UserRequestDto;
import hexlet.code.app.dtos.UserResponseDto;
import hexlet.code.app.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @GetMapping
    public List<UserResponseDto> findAll() {
        return userService.findAll();
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
    public UserResponseDto updateById(@PathVariable Long id,
                                      @Valid @RequestBody UserRequestDto userRequestDto) {
        return userService.updateById(id, userRequestDto);
    }
}
