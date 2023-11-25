package hexlet.code.app.domain.service;

import hexlet.code.app.domain.dtos.UserRequestDto;
import hexlet.code.app.domain.dtos.UserResponseDto;
import hexlet.code.app.domain.mappers.UserMapper;
import hexlet.code.app.domain.model.User;
import hexlet.code.app.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public List<UserResponseDto> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserResponseDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return userMapper.toDto(user);
    }

    public UserResponseDto save(UserRequestDto userRequestDto) {
        String encodedPassword = passwordEncoder.encode(userRequestDto.getPassword());
        userRequestDto.setPassword(encodedPassword);
        User user = userMapper.toEntity(userRequestDto);
        user.setCreatedAt(Instant.now());
        User resultUser = userRepository.save(user);
        return userMapper.toDto(resultUser);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public UserResponseDto updateById(Long id, UserRequestDto userRequestDto) {
        User user = userRepository.findById(id).orElseThrow();
        User updated = userMapper.partialUpdate(userRequestDto, user);
        User saved = userRepository.save(updated);
        return userMapper.toDto(saved);
    }
}
