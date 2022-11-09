package io.fafee.serverless;

import io.fafee.serverless.dto.Foo;
import io.fafee.serverless.entity.FooEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface FooMapper {

    @Mapping(target = "bars", ignore = true)
    FooEntity dtoToEntity(Foo foo);
    Foo entityToDto(FooEntity entity);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "created", ignore = true),
            @Mapping(target = "version", ignore = true),
            @Mapping(target = "bars", ignore = true)
    })
    void updateFromDto(Foo foo, @MappingTarget FooEntity entity);

    default ZonedDateTime map(Instant value) {
        if (value == null) return null;
        return ZonedDateTime.ofInstant(value, ZoneId.of("Europe/Budapest"));
    }
}
