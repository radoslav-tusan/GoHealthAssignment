package com.gohealth.assignment.shared;

import java.util.List;
import java.util.stream.Collectors;

public interface MapperBase<CREATE_DTO extends CreateDtoBase, DTO extends DtoBase, ENTITY extends EntityBase> {

    ENTITY toEntity(CREATE_DTO dto);

    DTO toDto(ENTITY entity);

    default List<DTO> toDtoList(List<ENTITY> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }
}
