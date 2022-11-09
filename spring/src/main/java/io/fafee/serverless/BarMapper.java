package io.fafee.serverless;

import io.fafee.serverless.dto.Bar;
import io.fafee.serverless.entity.BarEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface BarMapper {

    @Mappings({
        @Mapping(target = "x", source = "coords.x"),
        @Mapping(target = "y", source = "coords.y"),
        @Mapping(target = "z", source = "coords.z"),
    })
    Bar entityToDto(BarEntity entity);

}
