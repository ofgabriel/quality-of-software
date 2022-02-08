/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import SuitDetailComponent from '@/entities/suit/suit-details.vue';
import SuitClass from '@/entities/suit/suit-details.component';
import SuitService from '@/entities/suit/suit.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Suit Management Detail Component', () => {
    let wrapper: Wrapper<SuitClass>;
    let comp: SuitClass;
    let suitServiceStub: SinonStubbedInstance<SuitService>;

    beforeEach(() => {
      suitServiceStub = sinon.createStubInstance<SuitService>(SuitService);

      wrapper = shallowMount<SuitClass>(SuitDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { suitService: () => suitServiceStub },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundSuit = { id: 123 };
        suitServiceStub.find.resolves(foundSuit);

        // WHEN
        comp.retrieveSuit(123);
        await comp.$nextTick();

        // THEN
        expect(comp.suit).toBe(foundSuit);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundSuit = { id: 123 };
        suitServiceStub.find.resolves(foundSuit);

        // WHEN
        comp.beforeRouteEnter({ params: { suitId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.suit).toBe(foundSuit);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        comp.previousState();
        await comp.$nextTick();

        expect(comp.$router.currentRoute.fullPath).toContain('/');
      });
    });
  });
});
