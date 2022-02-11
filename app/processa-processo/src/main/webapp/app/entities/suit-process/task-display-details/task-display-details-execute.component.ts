import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskDisplayDetailsService from './task-display-details.service';
import { TaskDisplayDetailsContext } from './task-display-details.model';

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
        shouldGroup: {},
      },
    },
  },
};

@Component({
  validations,
})
export default class TaskDisplayDetailsExecuteComponent extends Vue {
  private taskDisplayDetailsService: TaskDisplayDetailsService = new TaskDisplayDetailsService();
  private taskContext: TaskDisplayDetailsContext = {};
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
    });
  }

  public claimTaskInstance(taskInstanceId) {
    this.taskDisplayDetailsService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.taskDisplayDetailsService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }

  public initRelationships(): void {}
}
