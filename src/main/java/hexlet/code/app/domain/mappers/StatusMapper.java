package hexlet.code.app.domain.mappers;

import hexlet.code.app.domain.dtos.StatusResponse;
import hexlet.code.app.domain.model.Status;
import hexlet.code.app.domain.dtos.StatusRequest;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface StatusMapper {
    StatusResponse toDto(Status status);

    Status toEntity(StatusRequest statusRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Status partialUpdate(StatusRequest statusRequest, @MappingTarget Status status);
}