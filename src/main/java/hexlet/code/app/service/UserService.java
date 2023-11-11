package hexlet.code.app.service;

import hexlet.code.app.dtos.UserRequestDto;
import hexlet.code.app.dtos.UserResponseDto;
import hexlet.code.app.mappers.UserMapper;
import hexlet.code.app.model.User;
import hexlet.code.app.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

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
        int hashCode = userRequestDto.getPassword().hashCode();
        userRequestDto.setPassword(String.valueOf(hashCode));
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
