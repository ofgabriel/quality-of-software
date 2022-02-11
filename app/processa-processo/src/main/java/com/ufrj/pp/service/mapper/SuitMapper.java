package com.ufrj.pp.service.mapper;

import com.ufrj.pp.domain.*;
import com.ufrj.pp.service.dto.SuitDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Suit} and its DTO {@link SuitDTO}.
 */
@Mapper(componentModel = "spring", uses = { LawyerMapper.class, GroupMapper.class })
public interface SuitMapper extends EntityMapper<SuitDTO, Suit> {
    @Mapping(target = "watcher", source = "watcher", qualifiedByName = "name")
    @Mapping(target = "group", source = "group", qualifiedByName = "name")
    SuitDTO toDto(Suit s);
}
