package com.ufrj.pp.process.suitProcess;

import com.ufrj.pp.domain.Lawyer;
import com.ufrj.pp.domain.Suit;
import com.ufrj.pp.domain.SuitProcess;
import com.ufrj.pp.service.dto.LawyerDTO;
import com.ufrj.pp.service.dto.SuitDTO;
import com.ufrj.pp.service.dto.SuitProcessDTO;
import org.akip.service.mapper.ProcessInstanceMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class })
public interface TaskWatcherDefinitionMapper {
    @Mapping(target = "processInstance", source = "processInstance", qualifiedByName = "loadTaskContext")
    SuitProcessDTO toSuitProcessDTO(SuitProcess suitProcess);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "watcher", source = "watcher")
    SuitDTO toSuitDTO(Suit suit);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    LawyerDTO toLawyerDTO(Lawyer name);
}
