package com.ufrj.pp.service.mapper;

import com.ufrj.pp.domain.*;
import com.ufrj.pp.service.dto.SuitDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Suit} and its DTO {@link SuitDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SuitMapper extends EntityMapper<SuitDTO, Suit> {}
