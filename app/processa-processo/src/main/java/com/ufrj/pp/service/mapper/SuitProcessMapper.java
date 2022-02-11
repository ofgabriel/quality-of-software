package com.ufrj.pp.service.mapper;

import com.ufrj.pp.domain.*;
import com.ufrj.pp.service.dto.SuitProcessDTO;
import org.akip.service.mapper.ProcessInstanceMapper;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SuitProcess} and its DTO {@link SuitProcessDTO}.
 */
@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class, SuitMapper.class })
public interface SuitProcessMapper extends EntityMapper<SuitProcessDTO, SuitProcess> {
    @Mapping(target = "processInstance", source = "processInstance")
    @Mapping(target = "suit", source = "suit")
    SuitProcessDTO toDto(SuitProcess s);
}
