import { Component, Vue, Inject } from 'vue-property-decorator';

import { ILawyer } from '@/shared/model/lawyer.model';
import LawyerService from './lawyer.service';

@Component
export default class LawyerDetails extends Vue {
  @Inject('lawyerService') private lawyerService: () => LawyerService;
  public lawyer: ILawyer = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.lawyerId) {
        vm.retrieveLawyer(to.params.lawyerId);
      }
    });
  }

  public retrieveLawyer(lawyerId) {
    this.lawyerService()
      .find(lawyerId)
      .then(res => {
        this.lawyer = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
