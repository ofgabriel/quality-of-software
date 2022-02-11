package com.ufrj.pp.process.suitProcess;

import com.ufrj.pp.service.dto.SuitProcessDTO;
import org.akip.service.dto.TaskInstanceDTO;

public class TaskDisplayDetailsContextDTO {

    private SuitProcessDTO suitProcess;
    private TaskInstanceDTO taskInstance;

    public SuitProcessDTO getSuitProcess() {
        return suitProcess;
    }

    public void setSuitProcess(SuitProcessDTO suitProcess) {
        this.suitProcess = suitProcess;
    }

    public TaskInstanceDTO getTaskInstance() {
        return taskInstance;
    }

    public void setTaskInstance(TaskInstanceDTO taskInstance) {
        this.taskInstance = taskInstance;
    }
}
