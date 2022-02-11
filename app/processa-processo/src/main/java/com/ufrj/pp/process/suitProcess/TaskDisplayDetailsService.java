package com.ufrj.pp.process.suitProcess;

import com.ufrj.pp.repository.SuitProcessRepository;
import com.ufrj.pp.service.SuitService;
import com.ufrj.pp.service.dto.SuitDTO;
import com.ufrj.pp.service.dto.SuitProcessDTO;
import com.ufrj.pp.service.mapper.SuitProcessMapper;
import org.akip.repository.TaskInstanceRepository;
import org.akip.service.TaskInstanceService;
import org.akip.service.dto.TaskInstanceDTO;
import org.akip.service.mapper.TaskInstanceMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskDisplayDetailsService {

    private final TaskInstanceService taskInstanceService;

    private final SuitService suitService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final SuitProcessRepository suitProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final TaskDisplayDetailsMapper taskDisplayDetailsMapper;

    private final SuitProcessMapper suitProcessMapper;

    public TaskDisplayDetailsService(
        TaskInstanceService taskInstanceService,
        SuitService suitService,
        TaskInstanceRepository taskInstanceRepository,
        SuitProcessRepository suitProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        TaskDisplayDetailsMapper taskDisplayDetailsMapper,
        SuitProcessMapper suitProcessMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.suitService = suitService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.suitProcessRepository = suitProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.taskDisplayDetailsMapper = taskDisplayDetailsMapper;
        this.suitProcessMapper = suitProcessMapper;
    }

    public TaskDisplayDetailsContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        SuitProcessDTO suitProcess = suitProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(taskDisplayDetailsMapper::toSuitProcessDTO)
            .orElseThrow();

        TaskDisplayDetailsContextDTO taskDisplayDetailsContext = new TaskDisplayDetailsContextDTO();
        taskDisplayDetailsContext.setTaskInstance(taskInstanceDTO);
        taskDisplayDetailsContext.setSuitProcess(suitProcess);

        return taskDisplayDetailsContext;
    }

    public TaskDisplayDetailsContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(TaskDisplayDetailsContextDTO taskDisplayDetailsContext) {
        SuitDTO suitDTO = suitService.findOne(taskDisplayDetailsContext.getSuitProcess().getSuit().getId()).orElseThrow();
        suitDTO.setSuitNumber(taskDisplayDetailsContext.getSuitProcess().getSuit().getSuitNumber());
        suitDTO.setName(taskDisplayDetailsContext.getSuitProcess().getSuit().getName());
        suitDTO.setDate(taskDisplayDetailsContext.getSuitProcess().getSuit().getDate());
        suitDTO.setClaimed(taskDisplayDetailsContext.getSuitProcess().getSuit().getClaimed());
        suitDTO.setLawyer(taskDisplayDetailsContext.getSuitProcess().getSuit().getLawyer());
        suitDTO.setCourt(taskDisplayDetailsContext.getSuitProcess().getSuit().getCourt());
        suitDTO.setValue(taskDisplayDetailsContext.getSuitProcess().getSuit().getValue());
        suitDTO.setShouldGroup(taskDisplayDetailsContext.getSuitProcess().getSuit().getShouldGroup());
        suitService.save(suitDTO);
    }

    public void complete(TaskDisplayDetailsContextDTO taskDisplayDetailsContext) {
        save(taskDisplayDetailsContext);
        SuitProcessDTO suitProcess = suitProcessRepository
            .findByProcessInstanceId(taskDisplayDetailsContext.getSuitProcess().getProcessInstance().getId())
            .map(suitProcessMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(taskDisplayDetailsContext.getTaskInstance(), suitProcess);
    }
}
