package hexlet.code.domain.mappers;

import hexlet.code.domain.dtos.StatusResponse;
import hexlet.code.domain.model.Status;
import hexlet.code.domain.dtos.StatusRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface StatusMapper {
    StatusResponse toDto(Status status);

    Status toEntity(StatusRequest statusRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Status partialUpdate(StatusRequest statusRequest, @MappingTarget Status status);
}
