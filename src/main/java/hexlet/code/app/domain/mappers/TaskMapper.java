package hexlet.code.app.domain.mappers;

import hexlet.code.app.domain.dtos.TaskRequest;
import hexlet.code.app.domain.dtos.TaskResponse;
import hexlet.code.app.domain.model.Label;
import hexlet.code.app.domain.model.Task;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TaskMapper {
    @Mapping(source = "content", target = "description")
    @Mapping(source = "title", target = "name")
    @Mapping(target = "assignee", ignore = true)
    @Mapping(target = "labels", ignore = true)
    @Mapping(target = "taskStatus", ignore = true)
    Task toTask(TaskRequest taskRequest);

    @InheritConfiguration(name = "toTask")
    Task partialUpdate(TaskRequest taskRequest, @MappingTarget Task task);

    @Mapping(source = "taskStatus.slug", target = "status")
    @Mapping(source = "description", target = "content")
    @Mapping(source = "name", target = "title")
    @Mapping(source = "assignee.id", target = "assigneeId")
    @Mapping(target = "taskLabelIds", expression = "java(labelsToLabelIds(task.getLabels()))")
    TaskResponse toTaskResponse(Task task);

    default Set<Long> labelsToLabelIds(Set<Label> labels) {
        return labels.stream()
                .map(Label::getId)
                .collect(Collectors.toSet());
    }
}
