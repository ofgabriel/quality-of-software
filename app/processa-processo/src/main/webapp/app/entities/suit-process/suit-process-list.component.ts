import { Component, Vue, Inject } from 'vue-property-decorator';
import { ISuitProcess } from '@/shared/model/suit-process.model';

import { ProcessDefinition, ProcessDefinitionService } from 'akip-vue-community';

import SuitProcessService from './suit-process.service';

@Component
export default class SuitProcessListComponent extends Vue {
  @Inject('suitProcessService') private suitProcessService: () => SuitProcessService;

  private processDefinitionService: ProcessDefinitionService = new ProcessDefinitionService();

  public bpmnProcessDefinitionId: string = 'suit_monitoring';
  public processDefinition: ProcessDefinition = new ProcessDefinition();
  public suitProcessList: ISuitProcess[] = [];

  public isFetchingProcessDefinition = false;
  public isFetchingProcessInstances = false;

  public mounted(): void {
    this.init();
  }

  public init(): void {
    this.retrieveProcessDefinition();
    this.retrieveProcessInstances();
  }

  public retrieveProcessDefinition() {
    this.isFetchingProcessDefinition = true;
    this.processDefinitionService.find(this.bpmnProcessDefinitionId).then(
      res => {
        this.processDefinition = res;
        this.isFetchingProcessDefinition = false;
      },
      err => {
        this.isFetchingProcessDefinition = false;
      }
    );
  }

  public retrieveProcessInstances(): void {
    this.isFetchingProcessInstances = true;
    this.suitProcessService()
      .retrieve()
      .then(
        res => {
          this.suitProcessList = res.data;
          this.isFetchingProcessInstances = false;
        },
        err => {
          this.isFetchingProcessInstances = false;
        }
      );
  }

  get isFetching(): boolean {
    return this.isFetchingProcessDefinition && this.isFetchingProcessInstances;
  }

  public handleSyncList(): void {
    this.retrieveProcessInstances();
  }

  public previousState(): void {
    this.$router.go(-1);
  }
}
