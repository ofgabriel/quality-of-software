package com.ufrj.pp.process.suitProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/suit-process/task-display-details")
public class TaskDisplayDetailsController {

    private final Logger log = LoggerFactory.getLogger(TaskDisplayDetailsController.class);

    private final TaskDisplayDetailsService taskDisplayDetailsService;

    public TaskDisplayDetailsController(TaskDisplayDetailsService taskDisplayDetailsService) {
        this.taskDisplayDetailsService = taskDisplayDetailsService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDisplayDetailsContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskDisplayDetailsContextDTO taskDisplayDetailsContext = taskDisplayDetailsService.loadContext(id);
        return ResponseEntity.ok(taskDisplayDetailsContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<TaskDisplayDetailsContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskDisplayDetailsContextDTO taskDisplayDetailsContext = taskDisplayDetailsService.claim(id);
        return ResponseEntity.ok(taskDisplayDetailsContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody TaskDisplayDetailsContextDTO taskDisplayDetailsContext) {
        log.debug("REST request to complete SuitProcess.TaskDisplayDetails {}", taskDisplayDetailsContext.getTaskInstance().getId());
        taskDisplayDetailsService.complete(taskDisplayDetailsContext);
        return ResponseEntity.noContent().build();
    }
}
