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
public class TaskGroupDefinitionService {

    private final TaskInstanceService taskInstanceService;

    private final SuitService suitService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final SuitProcessRepository suitProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final TaskGroupDefinitionMapper taskGroupDefinitionMapper;

    private final SuitProcessMapper suitProcessMapper;

    public TaskGroupDefinitionService(
        TaskInstanceService taskInstanceService,
        SuitService suitService,
        TaskInstanceRepository taskInstanceRepository,
        SuitProcessRepository suitProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        TaskGroupDefinitionMapper taskGroupDefinitionMapper,
        SuitProcessMapper suitProcessMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.suitService = suitService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.suitProcessRepository = suitProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.taskGroupDefinitionMapper = taskGroupDefinitionMapper;
        this.suitProcessMapper = suitProcessMapper;
    }

    public TaskGroupDefinitionContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        SuitProcessDTO suitProcess = suitProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(taskGroupDefinitionMapper::toSuitProcessDTO)
            .orElseThrow();

        TaskGroupDefinitionContextDTO taskGroupDefinitionContext = new TaskGroupDefinitionContextDTO();
        taskGroupDefinitionContext.setTaskInstance(taskInstanceDTO);
        taskGroupDefinitionContext.setSuitProcess(suitProcess);

        return taskGroupDefinitionContext;
    }

    public TaskGroupDefinitionContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(TaskGroupDefinitionContextDTO taskGroupDefinitionContext) {
        SuitDTO suitDTO = suitService.findOne(taskGroupDefinitionContext.getSuitProcess().getSuit().getId()).orElseThrow();
        suitDTO.setGroup(taskGroupDefinitionContext.getSuitProcess().getSuit().getGroup());
        suitService.save(suitDTO);
    }

    public void complete(TaskGroupDefinitionContextDTO taskGroupDefinitionContext) {
        save(taskGroupDefinitionContext);
        SuitProcessDTO suitProcess = suitProcessRepository
            .findByProcessInstanceId(taskGroupDefinitionContext.getSuitProcess().getProcessInstance().getId())
            .map(suitProcessMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(taskGroupDefinitionContext.getTaskInstance(), suitProcess);
    }
}
