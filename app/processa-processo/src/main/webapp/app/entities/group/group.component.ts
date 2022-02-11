import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IGroup } from '@/shared/model/group.model';

import GroupService from './group.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Group extends Vue {
  @Inject('groupService') private groupService: () => GroupService;
  private removeId: number = null;

  public groups: IGroup[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllGroups();
  }

  public clear(): void {
    this.retrieveAllGroups();
  }

  public retrieveAllGroups(): void {
    this.isFetching = true;

    this.groupService()
      .retrieve()
      .then(
        res => {
          this.groups = res.data;
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
