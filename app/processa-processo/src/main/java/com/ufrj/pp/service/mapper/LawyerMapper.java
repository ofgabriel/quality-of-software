package com.ufrj.pp.service.mapper;

import com.ufrj.pp.domain.*;
import com.ufrj.pp.service.dto.LawyerDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Lawyer} and its DTO {@link LawyerDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LawyerMapper extends EntityMapper<LawyerDTO, Lawyer> {
    @Named("name")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    LawyerDTO toDtoName(Lawyer lawyer);
}
