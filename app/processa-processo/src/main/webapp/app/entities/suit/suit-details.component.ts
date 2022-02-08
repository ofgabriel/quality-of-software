import { Component, Vue, Inject } from 'vue-property-decorator';

import { ISuit } from '@/shared/model/suit.model';
import SuitService from './suit.service';

@Component
export default class SuitDetails extends Vue {
  @Inject('suitService') private suitService: () => SuitService;
  public suit: ISuit = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.suitId) {
        vm.retrieveSuit(to.params.suitId);
      }
    });
  }

  public retrieveSuit(suitId) {
    this.suitService()
      .find(suitId)
      .then(res => {
        this.suit = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
