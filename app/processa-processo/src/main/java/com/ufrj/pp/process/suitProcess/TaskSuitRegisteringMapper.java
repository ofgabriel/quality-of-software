package com.ufrj.pp.process.suitProcess;

import com.ufrj.pp.domain.Suit;
import com.ufrj.pp.domain.SuitProcess;
import com.ufrj.pp.service.dto.SuitDTO;
import com.ufrj.pp.service.dto.SuitProcessDTO;
import org.akip.service.mapper.ProcessInstanceMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class })
public interface TaskSuitRegisteringMapper {
    @Mapping(target = "processInstance", source = "processInstance", qualifiedByName = "loadTaskContext")
    SuitProcessDTO toSuitProcessDTO(SuitProcess suitProcess);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "suitNumber", source = "suitNumber")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "date", source = "date")
    @Mapping(target = "claimed", source = "claimed")
    @Mapping(target = "lawyer", source = "lawyer")
    @Mapping(target = "court", source = "court")
    @Mapping(target = "value", source = "value")
    SuitDTO toSuitDTO(Suit suit);
}
