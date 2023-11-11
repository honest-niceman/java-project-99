package hexlet.code.app.mappers;

import hexlet.code.app.model.User;
import hexlet.code.app.dtos.UserRequestDto;
import hexlet.code.app.dtos.UserResponseDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {
    UserResponseDto toDto(User user);

    User toEntity(UserRequestDto userRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserRequestDto userRequestDto, @MappingTarget User user);
}
