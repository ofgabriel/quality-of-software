/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import LawyerComponent from '@/entities/lawyer/lawyer.vue';
import LawyerClass from '@/entities/lawyer/lawyer.component';
import LawyerService from '@/entities/lawyer/lawyer.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-badge', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  describe('Lawyer Management Component', () => {
    let wrapper: Wrapper<LawyerClass>;
    let comp: LawyerClass;
    let lawyerServiceStub: SinonStubbedInstance<LawyerService>;

    beforeEach(() => {
      lawyerServiceStub = sinon.createStubInstance<LawyerService>(LawyerService);
      lawyerServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<LawyerClass>(LawyerComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          lawyerService: () => lawyerServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      lawyerServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllLawyers();
      await comp.$nextTick();

      // THEN
      expect(lawyerServiceStub.retrieve.called).toBeTruthy();
      expect(comp.lawyers[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
