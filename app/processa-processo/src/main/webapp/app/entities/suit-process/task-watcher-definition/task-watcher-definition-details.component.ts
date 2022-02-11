import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskWatcherDefinitionService from './task-watcher-definition.service';
import { TaskWatcherDefinitionContext } from './task-watcher-definition.model';

@Component
export default class TaskWatcherDefinitionDetailsComponent extends Vue {
  private taskWatcherDefinitionService: TaskWatcherDefinitionService = new TaskWatcherDefinitionService();
  private taskContext: TaskWatcherDefinitionContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.taskWatcherDefinitionService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
