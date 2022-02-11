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
public class TaskSuitRegisteringService {

    private final TaskInstanceService taskInstanceService;

    private final SuitService suitService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final SuitProcessRepository suitProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final TaskSuitRegisteringMapper taskSuitRegisteringMapper;

    private final SuitProcessMapper suitProcessMapper;

    public TaskSuitRegisteringService(
        TaskInstanceService taskInstanceService,
        SuitService suitService,
        TaskInstanceRepository taskInstanceRepository,
        SuitProcessRepository suitProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        TaskSuitRegisteringMapper taskSuitRegisteringMapper,
        SuitProcessMapper suitProcessMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.suitService = suitService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.suitProcessRepository = suitProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.taskSuitRegisteringMapper = taskSuitRegisteringMapper;
        this.suitProcessMapper = suitProcessMapper;
    }

    public TaskSuitRegisteringContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        SuitProcessDTO suitProcess = suitProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(taskSuitRegisteringMapper::toSuitProcessDTO)
            .orElseThrow();

        TaskSuitRegisteringContextDTO taskSuitRegisteringContext = new TaskSuitRegisteringContextDTO();
        taskSuitRegisteringContext.setTaskInstance(taskInstanceDTO);
        taskSuitRegisteringContext.setSuitProcess(suitProcess);

        return taskSuitRegisteringContext;
    }

    public TaskSuitRegisteringContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(TaskSuitRegisteringContextDTO taskSuitRegisteringContext) {
        SuitDTO suitDTO = suitService.findOne(taskSuitRegisteringContext.getSuitProcess().getSuit().getId()).orElseThrow();
        suitDTO.setSuitNumber(taskSuitRegisteringContext.getSuitProcess().getSuit().getSuitNumber());
        suitDTO.setName(taskSuitRegisteringContext.getSuitProcess().getSuit().getName());
        suitDTO.setDate(taskSuitRegisteringContext.getSuitProcess().getSuit().getDate());
        suitDTO.setClaimed(taskSuitRegisteringContext.getSuitProcess().getSuit().getClaimed());
        suitDTO.setLawyer(taskSuitRegisteringContext.getSuitProcess().getSuit().getLawyer());
        suitDTO.setCourt(taskSuitRegisteringContext.getSuitProcess().getSuit().getCourt());
        suitDTO.setValue(taskSuitRegisteringContext.getSuitProcess().getSuit().getValue());
        suitService.save(suitDTO);
    }

    public void complete(TaskSuitRegisteringContextDTO taskSuitRegisteringContext) {
        save(taskSuitRegisteringContext);
        SuitProcessDTO suitProcess = suitProcessRepository
            .findByProcessInstanceId(taskSuitRegisteringContext.getSuitProcess().getProcessInstance().getId())
            .map(suitProcessMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(taskSuitRegisteringContext.getTaskInstance(), suitProcess);
    }
}
