package hexlet.code.app.domain.mappers;

import hexlet.code.app.domain.dtos.UserRequestDto;
import hexlet.code.app.domain.dtos.UserResponseDto;
import hexlet.code.app.domain.model.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {
    UserResponseDto toDto(User user);

    User toEntity(UserRequestDto userRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserRequestDto userRequestDto, @MappingTarget User user);
}
