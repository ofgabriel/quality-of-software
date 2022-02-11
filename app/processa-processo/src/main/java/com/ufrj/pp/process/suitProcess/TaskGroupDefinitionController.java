package com.ufrj.pp.process.suitProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/suit-process/task-group-definition")
public class TaskGroupDefinitionController {

    private final Logger log = LoggerFactory.getLogger(TaskGroupDefinitionController.class);

    private final TaskGroupDefinitionService taskGroupDefinitionService;

    public TaskGroupDefinitionController(TaskGroupDefinitionService taskGroupDefinitionService) {
        this.taskGroupDefinitionService = taskGroupDefinitionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskGroupDefinitionContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskGroupDefinitionContextDTO taskGroupDefinitionContext = taskGroupDefinitionService.loadContext(id);
        return ResponseEntity.ok(taskGroupDefinitionContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<TaskGroupDefinitionContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskGroupDefinitionContextDTO taskGroupDefinitionContext = taskGroupDefinitionService.claim(id);
        return ResponseEntity.ok(taskGroupDefinitionContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody TaskGroupDefinitionContextDTO taskGroupDefinitionContext) {
        log.debug("REST request to complete SuitProcess.TaskGroupDefinition {}", taskGroupDefinitionContext.getTaskInstance().getId());
        taskGroupDefinitionService.complete(taskGroupDefinitionContext);
        return ResponseEntity.noContent().build();
    }
}
