import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskSuitRegisteringService from './task-suit-registering.service';
import { TaskSuitRegisteringContext } from './task-suit-registering.model';

const validations: any = {
  taskContext: {
    suitProcess: {
      suit: {
        suitNumber: {},
        name: {},
        date: {},
        claimed: {},
        lawyer: {},
        court: {},
        value: {},
      },
    },
  },
};

@Component({
  validations,
})
export default class TaskSuitRegisteringExecuteComponent extends Vue {
  private taskSuitRegisteringService: TaskSuitRegisteringService = new TaskSuitRegisteringService();
  private taskContext: TaskSuitRegisteringContext = {};
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
    });
  }

  public claimTaskInstance(taskInstanceId) {
    this.taskSuitRegisteringService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.taskSuitRegisteringService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }

  public initRelationships(): void {}
}
