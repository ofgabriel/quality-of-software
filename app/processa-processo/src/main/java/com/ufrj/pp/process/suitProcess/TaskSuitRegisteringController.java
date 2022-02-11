package com.ufrj.pp.process.suitProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/suit-process/task-suit-registering")
public class TaskSuitRegisteringController {

    private final Logger log = LoggerFactory.getLogger(TaskSuitRegisteringController.class);

    private final TaskSuitRegisteringService taskSuitRegisteringService;

    public TaskSuitRegisteringController(TaskSuitRegisteringService taskSuitRegisteringService) {
        this.taskSuitRegisteringService = taskSuitRegisteringService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskSuitRegisteringContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskSuitRegisteringContextDTO taskSuitRegisteringContext = taskSuitRegisteringService.loadContext(id);
        return ResponseEntity.ok(taskSuitRegisteringContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<TaskSuitRegisteringContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskSuitRegisteringContextDTO taskSuitRegisteringContext = taskSuitRegisteringService.claim(id);
        return ResponseEntity.ok(taskSuitRegisteringContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody TaskSuitRegisteringContextDTO taskSuitRegisteringContext) {
        log.debug("REST request to complete SuitProcess.TaskSuitRegistering {}", taskSuitRegisteringContext.getTaskInstance().getId());
        taskSuitRegisteringService.complete(taskSuitRegisteringContext);
        return ResponseEntity.noContent().build();
    }
}
