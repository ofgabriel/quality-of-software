import { Component, Vue, Inject } from 'vue-property-decorator';

import { required } from 'vuelidate/lib/validators';

import LawyerService from '@/entities/lawyer/lawyer.service';
import { ILawyer } from '@/shared/model/lawyer.model';

import TaskWatcherDefinitionService from './task-watcher-definition.service';
import { TaskWatcherDefinitionContext } from './task-watcher-definition.model';

const validations: any = {
  taskContext: {
    suitProcess: {
      suit: {
        shouldReceiveNotification: {},
        watcher: {
          required,
        },
      },
    },
  },
};

@Component({
  validations,
})
export default class TaskWatcherDefinitionExecuteComponent extends Vue {
  private taskWatcherDefinitionService: TaskWatcherDefinitionService = new TaskWatcherDefinitionService();
  private taskContext: TaskWatcherDefinitionContext = {};

  @Inject('lawyerService') private lawyerService: () => LawyerService;

  public lawyers: ILawyer[] = [];
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
      vm.initRelationships();
    });
  }

  public claimTaskInstance(taskInstanceId) {
    this.taskWatcherDefinitionService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.taskWatcherDefinitionService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }

  public initRelationships(): void {
    this.lawyerService()
      .retrieve()
      .then(res => {
        this.lawyers = res.data;
      });
  }
}
