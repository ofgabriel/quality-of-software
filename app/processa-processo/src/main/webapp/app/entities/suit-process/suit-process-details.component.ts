import { Component, Vue, Inject } from 'vue-property-decorator';

import { ISuitProcess } from '@/shared/model/suit-process.model';
import SuitProcessService from './suit-process.service';

@Component
export default class SuitProcessDetailsComponent extends Vue {
  @Inject('suitProcessService') private suitProcessService: () => SuitProcessService;
  public suitProcess: ISuitProcess = {};

  public isFetching: boolean = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.processInstanceId) {
        vm.retrieveSuitProcess(to.params.processInstanceId);
      }
    });
  }

  public retrieveSuitProcess(suitProcessId) {
    this.isFetching = true;
    this.suitProcessService()
      .find(suitProcessId)
      .then(
        res => {
          this.suitProcess = res;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public previousState() {
    this.$router.go(-1);
  }
}
