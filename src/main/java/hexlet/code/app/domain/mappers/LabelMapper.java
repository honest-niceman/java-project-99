package hexlet.code.app.domain.mappers;

import hexlet.code.app.domain.dtos.LabelRequest;
import hexlet.code.app.domain.dtos.LabelResponse;
import hexlet.code.app.domain.model.Label;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface LabelMapper {
    LabelResponse toLabelResponse(Label label);

    Label toEntity(LabelRequest labelRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Label partialUpdate(LabelRequest labelRequest, @MappingTarget Label label);
}
