/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import SuitComponent from '@/entities/suit/suit.vue';
import SuitClass from '@/entities/suit/suit.component';
import SuitService from '@/entities/suit/suit.service';

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
  describe('Suit Management Component', () => {
    let wrapper: Wrapper<SuitClass>;
    let comp: SuitClass;
    let suitServiceStub: SinonStubbedInstance<SuitService>;

    beforeEach(() => {
      suitServiceStub = sinon.createStubInstance<SuitService>(SuitService);
      suitServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<SuitClass>(SuitComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          suitService: () => suitServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      suitServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllSuits();
      await comp.$nextTick();

      // THEN
      expect(suitServiceStub.retrieve.called).toBeTruthy();
      expect(comp.suits[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
