import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ISuit } from '@/shared/model/suit.model';

import SuitService from './suit.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Suit extends Vue {
  @Inject('suitService') private suitService: () => SuitService;
  private removeId: number = null;

  public suits: ISuit[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllSuits();
  }

  public clear(): void {
    this.retrieveAllSuits();
  }

  public retrieveAllSuits(): void {
    this.isFetching = true;

    this.suitService()
      .retrieve()
      .then(
        res => {
          this.suits = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public handleSyncList(): void {
    this.clear();
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
