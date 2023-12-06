package hexlet.code.service;

import hexlet.code.dto.TaskStatusRequest;
import hexlet.code.dto.TaskStatusResponse;
import hexlet.code.mapper.StatusMapper;
import hexlet.code.model.TaskStatus;
import hexlet.code.repository.TaskStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StatusService {

    private final TaskStatusRepository taskStatusRepository;
    private final StatusMapper statusMapper;

    public TaskStatusResponse findById(Long id) {
        TaskStatus taskStatus = taskStatusRepository.findById(id).orElseThrow();
        return statusMapper.toDto(taskStatus);
    }

    public List<TaskStatusResponse> findAll() {
        return taskStatusRepository.findAll().stream().map(statusMapper::toDto).toList();
    }

    public TaskStatusResponse save(TaskStatusRequest taskStatusRequest) {
        TaskStatus taskStatus = statusMapper.toEntity(taskStatusRequest);
        TaskStatus saved = taskStatusRepository.save(taskStatus);
        return statusMapper.toDto(saved);
    }

    public TaskStatusResponse updateById(Long id, TaskStatusRequest taskStatusRequest) {
        TaskStatus taskStatus = taskStatusRepository.findById(id).orElseThrow();
        TaskStatus updated = statusMapper.partialUpdate(taskStatusRequest, taskStatus);
        TaskStatus saved = taskStatusRepository.save(updated);
        return statusMapper.toDto(saved);
    }

    public void deleteById(Long id) {
        taskStatusRepository.deleteById(id);
    }
}
