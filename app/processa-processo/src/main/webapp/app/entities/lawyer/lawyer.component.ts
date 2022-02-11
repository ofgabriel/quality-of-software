import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ILawyer } from '@/shared/model/lawyer.model';

import LawyerService from './lawyer.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Lawyer extends Vue {
  @Inject('lawyerService') private lawyerService: () => LawyerService;
  private removeId: number = null;

  public lawyers: ILawyer[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllLawyers();
  }

  public clear(): void {
    this.retrieveAllLawyers();
  }

  public retrieveAllLawyers(): void {
    this.isFetching = true;

    this.lawyerService()
      .retrieve()
      .then(
        res => {
          this.lawyers = res.data;
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
