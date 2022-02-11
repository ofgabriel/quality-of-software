package com.ufrj.pp.process.suitProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/suit-process/task-watcher-definition")
public class TaskWatcherDefinitionController {

    private final Logger log = LoggerFactory.getLogger(TaskWatcherDefinitionController.class);

    private final TaskWatcherDefinitionService taskWatcherDefinitionService;

    public TaskWatcherDefinitionController(TaskWatcherDefinitionService taskWatcherDefinitionService) {
        this.taskWatcherDefinitionService = taskWatcherDefinitionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskWatcherDefinitionContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskWatcherDefinitionContextDTO taskWatcherDefinitionContext = taskWatcherDefinitionService.loadContext(id);
        return ResponseEntity.ok(taskWatcherDefinitionContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<TaskWatcherDefinitionContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskWatcherDefinitionContextDTO taskWatcherDefinitionContext = taskWatcherDefinitionService.claim(id);
        return ResponseEntity.ok(taskWatcherDefinitionContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody TaskWatcherDefinitionContextDTO taskWatcherDefinitionContext) {
        log.debug("REST request to complete SuitProcess.TaskWatcherDefinition {}", taskWatcherDefinitionContext.getTaskInstance().getId());
        taskWatcherDefinitionService.complete(taskWatcherDefinitionContext);
        return ResponseEntity.noContent().build();
    }
}
