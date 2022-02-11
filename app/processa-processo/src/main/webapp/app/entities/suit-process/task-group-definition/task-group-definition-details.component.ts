import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskGroupDefinitionService from './task-group-definition.service';
import { TaskGroupDefinitionContext } from './task-group-definition.model';

@Component
export default class TaskGroupDefinitionDetailsComponent extends Vue {
  private taskGroupDefinitionService: TaskGroupDefinitionService = new TaskGroupDefinitionService();
  private taskContext: TaskGroupDefinitionContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.taskGroupDefinitionService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
