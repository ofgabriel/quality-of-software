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
public class TaskWatcherDefinitionService {

    private final TaskInstanceService taskInstanceService;

    private final SuitService suitService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final SuitProcessRepository suitProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final TaskWatcherDefinitionMapper taskWatcherDefinitionMapper;

    private final SuitProcessMapper suitProcessMapper;

    public TaskWatcherDefinitionService(
        TaskInstanceService taskInstanceService,
        SuitService suitService,
        TaskInstanceRepository taskInstanceRepository,
        SuitProcessRepository suitProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        TaskWatcherDefinitionMapper taskWatcherDefinitionMapper,
        SuitProcessMapper suitProcessMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.suitService = suitService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.suitProcessRepository = suitProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.taskWatcherDefinitionMapper = taskWatcherDefinitionMapper;
        this.suitProcessMapper = suitProcessMapper;
    }

    public TaskWatcherDefinitionContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        SuitProcessDTO suitProcess = suitProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(taskWatcherDefinitionMapper::toSuitProcessDTO)
            .orElseThrow();

        TaskWatcherDefinitionContextDTO taskWatcherDefinitionContext = new TaskWatcherDefinitionContextDTO();
        taskWatcherDefinitionContext.setTaskInstance(taskInstanceDTO);
        taskWatcherDefinitionContext.setSuitProcess(suitProcess);

        return taskWatcherDefinitionContext;
    }

    public TaskWatcherDefinitionContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(TaskWatcherDefinitionContextDTO taskWatcherDefinitionContext) {
        SuitDTO suitDTO = suitService.findOne(taskWatcherDefinitionContext.getSuitProcess().getSuit().getId()).orElseThrow();
        suitDTO.setShouldReceiveNotification(taskWatcherDefinitionContext.getSuitProcess().getSuit().getShouldReceiveNotification());
        suitDTO.setWatcher(taskWatcherDefinitionContext.getSuitProcess().getSuit().getWatcher());
        suitService.save(suitDTO);
    }

    public void complete(TaskWatcherDefinitionContextDTO taskWatcherDefinitionContext) {
        save(taskWatcherDefinitionContext);
        SuitProcessDTO suitProcess = suitProcessRepository
            .findByProcessInstanceId(taskWatcherDefinitionContext.getSuitProcess().getProcessInstance().getId())
            .map(suitProcessMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(taskWatcherDefinitionContext.getTaskInstance(), suitProcess);
    }
}
