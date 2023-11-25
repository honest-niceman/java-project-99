package hexlet.code.app.domain.mappers;

import hexlet.code.app.domain.dtos.UserRequestDto;
import hexlet.code.app.domain.dtos.UserResponseDto;
import hexlet.code.app.domain.model.User;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "email", target = "username")
    UserResponseDto toDto(User user);

    @Mapping(source = "username", target = "email")
    User toEntity(UserRequestDto userRequestDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "username", target = "email")
    User partialUpdate(UserRequestDto userRequestDto, @MappingTarget User user);
}
