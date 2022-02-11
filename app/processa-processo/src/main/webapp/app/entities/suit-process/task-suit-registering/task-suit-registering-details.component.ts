import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskSuitRegisteringService from './task-suit-registering.service';
import { TaskSuitRegisteringContext } from './task-suit-registering.model';

@Component
export default class TaskSuitRegisteringDetailsComponent extends Vue {
  private taskSuitRegisteringService: TaskSuitRegisteringService = new TaskSuitRegisteringService();
  private taskContext: TaskSuitRegisteringContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.taskSuitRegisteringService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
