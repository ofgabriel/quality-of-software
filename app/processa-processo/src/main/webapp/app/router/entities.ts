import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore

// prettier-ignore
const Suit = () => import('@/entities/suit/suit.vue');
// prettier-ignore
const SuitDetails = () => import('@/entities/suit/suit-details.vue');
// prettier-ignore
const SuitProcessDetails = () => import('@/entities/suit-process/suit-process-details.vue');
// prettier-ignore
const SuitProcessList = () => import('@/entities/suit-process/suit-process-list.vue');
// prettier-ignore
const Lawyer = () => import('@/entities/lawyer/lawyer.vue');
// prettier-ignore
const LawyerDetails = () => import('@/entities/lawyer/lawyer-details.vue');
// prettier-ignore
const SuitStartFormInit = () => import('@/entities/suit-process/suit-start-form-init.vue');
// prettier-ignore
const SuitProcess_TaskSuitRegisteringDetails = () => import('@/entities/suit-process/task-suit-registering/task-suit-registering-details.vue');
// prettier-ignore
const SuitProcess_TaskSuitRegisteringExecute = () => import('@/entities/suit-process/task-suit-registering/task-suit-registering-execute.vue');
// prettier-ignore
const SuitProcess_TaskWatcherDefinitionDetails = () => import('@/entities/suit-process/task-watcher-definition/task-watcher-definition-details.vue');
// prettier-ignore
const SuitProcess_TaskWatcherDefinitionExecute = () => import('@/entities/suit-process/task-watcher-definition/task-watcher-definition-execute.vue');
// prettier-ignore
const SuitProcess_TaskDisplayDetailsDetails = () => import('@/entities/suit-process/task-display-details/task-display-details-details.vue');
// prettier-ignore
const SuitProcess_TaskDisplayDetailsExecute = () => import('@/entities/suit-process/task-display-details/task-display-details-execute.vue');
// prettier-ignore
const Group = () => import('@/entities/group/group.vue');
// prettier-ignore
const GroupDetails = () => import('@/entities/group/group-details.vue');
// prettier-ignore
const SuitProcess_TaskGroupDefinitionDetails = () => import('@/entities/suit-process/task-group-definition/task-group-definition-details.vue');
// prettier-ignore
const SuitProcess_TaskGroupDefinitionExecute = () => import('@/entities/suit-process/task-group-definition/task-group-definition-execute.vue');
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
  {
    path: '/process-definition/suit_monitoring/instance/:processInstanceId/view',
    name: 'SuitProcessView',
    component: SuitProcessDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/suit_monitoring/instances',
    name: 'SuitProcessList',
    component: SuitProcessList,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/lawyer',
    name: 'Lawyer',
    component: Lawyer,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/lawyer/:lawyerId/view',
    name: 'LawyerView',
    component: LawyerDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/suit_monitoring/init',
    name: 'SuitStartFormInit',
    component: SuitStartFormInit,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/suit_monitoring/task/suit_registering/:taskInstanceId/view',
    name: 'SuitProcess_TaskSuitRegisteringDetails',
    component: SuitProcess_TaskSuitRegisteringDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/suit_monitoring/task/suit_registering/:taskInstanceId/execute',
    name: 'SuitProcess_TaskSuitRegisteringExecute',
    component: SuitProcess_TaskSuitRegisteringExecute,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/suit_monitoring/task/watcher_definition/:taskInstanceId/view',
    name: 'SuitProcess_TaskWatcherDefinitionDetails',
    component: SuitProcess_TaskWatcherDefinitionDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/suit_monitoring/task/watcher_definition/:taskInstanceId/execute',
    name: 'SuitProcess_TaskWatcherDefinitionExecute',
    component: SuitProcess_TaskWatcherDefinitionExecute,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/suit_monitoring/task/display_suit_details/:taskInstanceId/view',
    name: 'SuitProcess_TaskDisplayDetailsDetails',
    component: SuitProcess_TaskDisplayDetailsDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/suit_monitoring/task/display_suit_details/:taskInstanceId/execute',
    name: 'SuitProcess_TaskDisplayDetailsExecute',
    component: SuitProcess_TaskDisplayDetailsExecute,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/group',
    name: 'Group',
    component: Group,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/group/:groupId/view',
    name: 'GroupView',
    component: GroupDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/suit_monitoring/task/suit_grouping/:taskInstanceId/view',
    name: 'SuitProcess_TaskGroupDefinitionDetails',
    component: SuitProcess_TaskGroupDefinitionDetails,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/process-definition/suit_monitoring/task/suit_grouping/:taskInstanceId/execute',
    name: 'SuitProcess_TaskGroupDefinitionExecute',
    component: SuitProcess_TaskGroupDefinitionExecute,
    meta: { authorities: [Authority.USER] },
  },
  // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
];
