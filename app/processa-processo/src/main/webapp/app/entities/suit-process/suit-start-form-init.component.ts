import { Component, Vue, Inject } from 'vue-property-decorator';

import { ISuitProcess, SuitProcess } from '@/shared/model/suit-process.model';

import { ProcessInstance, ProcessDefinitionService } from 'akip-vue-community';

import { Suit } from '@/shared/model/suit.model';
import SuitProcessService from './suit-process.service';

const validations: any = {
  suitProcess: {
    suit: {
      suitNumber: {},
    },
  },
};

@Component({
  validations,
})
export default class SuitStartFormInitComponent extends Vue {
  @Inject('suitProcessService') private suitProcessService: () => SuitProcessService;

  private processDefinitionService: ProcessDefinitionService = new ProcessDefinitionService();

  public bpmnProcessDefinitionId: string = 'suit_monitoring';
  public suitProcess: ISuitProcess = new SuitProcess();

  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      vm.initSuitStartForm();
      vm.initRelationships();
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;

    this.suitProcessService()
      .create(this.suitProcess)
      .then(param => {
        this.isSaving = false;
        this.$router.go(-1);
        const message = this.$t('ppApp.suitStartForm.created', { param: param.id });
        this.$root.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Success',
          variant: 'success',
          solid: true,
          autoHideDelay: 5000,
        });
      });
  }

  public initSuitStartForm(): void {
    this.suitProcess.suit = new Suit();
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.processDefinitionService.find(this.bpmnProcessDefinitionId).then(res => {
      this.suitProcess.processInstance = new ProcessInstance();
      this.suitProcess.processInstance.processDefinition = res;
    });
  }
}
