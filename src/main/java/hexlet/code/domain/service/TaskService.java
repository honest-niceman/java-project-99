package hexlet.code.domain.service;

import hexlet.code.domain.dtos.TaskRequest;
import hexlet.code.domain.dtos.TaskResponse;
import hexlet.code.domain.mappers.TaskMapper;
import hexlet.code.domain.model.Label;
import hexlet.code.domain.model.Status;
import hexlet.code.domain.model.Task;
import hexlet.code.domain.model.User;
import hexlet.code.domain.repository.LabelRepository;
import hexlet.code.domain.repository.StatusRepository;
import hexlet.code.domain.repository.TaskRepository;
import hexlet.code.domain.repository.UserRepository;
import hexlet.code.domain.specification.TaskParams;
import hexlet.code.domain.specification.TaskSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final StatusRepository statusRepository;
    private final UserRepository userRepository;
    private final LabelRepository labelRepository;
    private final TaskSpecification taskSpecification;

    public TaskResponse findById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow();
        return taskMapper.toTaskResponse(task);
    }

    public List<TaskResponse> findAll(TaskParams taskParams) {
        Specification<Task> specification = taskSpecification.build(taskParams);
        return taskRepository.findAll(specification).stream().map(taskMapper::toTaskResponse).toList();
    }

    public TaskResponse save(TaskRequest taskRequest) {
        Task task = taskMapper.toTask(taskRequest);

        setAssociations(taskRequest, task);

        Task saved = taskRepository.save(task);
        return taskMapper.toTaskResponse(saved);
    }

    public TaskResponse updateById(Long id, TaskRequest taskRequest) {
        Task task = taskRepository.findById(id).orElseThrow();
        //update only basic fields
        Task updated = taskMapper.partialUpdate(taskRequest, task);

        setAssociations(taskRequest, updated);

        Task saved = taskRepository.save(updated);
        return taskMapper.toTaskResponse(saved);
    }

    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    private void setAssociations(TaskRequest taskRequest, Task task) {
        Status status = null;
        if (taskRequest.getStatus() != null) {
            status = statusRepository.findBySlug(taskRequest.getStatus()).orElseThrow();
        }
        User user = null;
        if (taskRequest.getAssigneeId() != null) {
            user = userRepository.findById(taskRequest.getAssigneeId()).orElseThrow();
        }
        List<Label> labels = null;
        if (taskRequest.getTaskLabelIds() != null) {
            labels = labelRepository.findAllById(taskRequest.getTaskLabelIds());
        }
        task.setTaskStatus(status);
        task.setAssignee(user);
        task.setLabels(labels != null ? new HashSet<>(labels) : new HashSet<>());
    }
}
