import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskDisplayDetailsService from './task-display-details.service';
import { TaskDisplayDetailsContext } from './task-display-details.model';

@Component
export default class TaskDisplayDetailsDetailsComponent extends Vue {
  private taskDisplayDetailsService: TaskDisplayDetailsService = new TaskDisplayDetailsService();
  private taskContext: TaskDisplayDetailsContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.taskDisplayDetailsService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
