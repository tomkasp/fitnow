package com.tomkasp.fitnow.infrastructure.factory;

/**
 * @author Tomasz Kasprzycki (A042191)
 */
public interface DtoFactory<DTO, ENTITY> {

    ENTITY toEntity(DTO dto);
    DTO toDto(ENTITY entity);
}
