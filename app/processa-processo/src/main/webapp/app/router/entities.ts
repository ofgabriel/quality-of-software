import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore

// prettier-ignore
const Suit = () => import('@/entities/suit/suit.vue');
// prettier-ignore
const SuitDetails = () => import('@/entities/suit/suit-details.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default [
  {
    path: '/suit',
    name: 'Suit',
    component: Suit,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/suit/:suitId/view',
    name: 'SuitView',
    component: SuitDetails,
    meta: { authorities: [Authority.USER] },
  },
  // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
];
