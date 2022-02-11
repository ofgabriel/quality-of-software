import { Component, Vue, Inject } from 'vue-property-decorator';

import { required } from 'vuelidate/lib/validators';

import GroupService from '@/entities/group/group.service';
import { IGroup } from '@/shared/model/group.model';

import TaskGroupDefinitionService from './task-group-definition.service';
import { TaskGroupDefinitionContext } from './task-group-definition.model';

const validations: any = {
  taskContext: {
    suitProcess: {
      suit: {
        group: {
          required,
        },
      },
    },
  },
};

@Component({
  validations,
})
export default class TaskGroupDefinitionExecuteComponent extends Vue {
  private taskGroupDefinitionService: TaskGroupDefinitionService = new TaskGroupDefinitionService();
  private taskContext: TaskGroupDefinitionContext = {};

  @Inject('groupService') private groupService: () => GroupService;

  public groups: IGroup[] = [];
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
    this.taskGroupDefinitionService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.taskGroupDefinitionService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }

  public initRelationships(): void {
    this.groupService()
      .retrieve()
      .then(res => {
        this.groups = res.data;
      });
  }
}
