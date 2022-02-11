<template>
  <div>
    <h2 class="jh-entity-heading" data-cy="suitProcessDetailsHeading">
      <span v-text="$t('ppApp.suitProcess.home.title')">SuitProcess</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('ppApp.suitProcess.home.refreshListLabel')">Refresh List</span>
        </button>

        <router-link :to="`/process-definition/suit_monitoring/init`" tag="button" class="btn btn-primary mr-2">
          <font-awesome-icon icon="plus"></font-awesome-icon>
          <span v-text="$t('ppApp.suitProcess.home.createLabel')"> Create a new Travel Plan Process Instance </span>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && suitProcessList && suitProcessList.length === 0">
      <span v-text="$t('ppApp.suitProcess.home.notFound')">No suitProcess found</span>
    </div>
    <div class="table-responsive" v-if="suitProcessList && suitProcessList.length > 0">
      <table class="table table-striped" aria-describedby="suitProcessList">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span>Process Instance</span></th>
            <th scope="row"><span>Suit</span></th>
            <th scope="row"><span>Status</span></th>
            <th scope="row"><span>Start Date</span></th>
            <th scope="row"><span>End Date</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="suitProcess in suitProcessList" :key="suitProcess.id" data-cy="entityTable">
            <td>{{ suitProcess.id }}</td>
            <td>
              <div v-if="suitProcess.processInstance">
                <router-link :to="`/process-definition/suit_monitoring/instance/${suitProcess.processInstance.id}/view`">
                  {{ suitProcess.processInstance.businessKey }}
                </router-link>
              </div>
            </td>
            <td>
              <div v-if="suitProcess.suit">
                <router-link :to="{ name: 'SuitView', params: { suitId: suitProcess.suit.id } }">{{ suitProcess.suit.id }}</router-link>
              </div>
            </td>
            <td>
              <akip-show-process-instance-status :status="suitProcess.processInstance.status"></akip-show-process-instance-status>
            </td>
            <td>{{ suitProcess.processInstance.startDate ? $d(Date.parse(suitProcess.processInstance.startDate), 'short') : '' }}</td>
            <td>{{ suitProcess.processInstance.endDate ? $d(Date.parse(suitProcess.processInstance.endDate), 'short') : '' }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="`/process-definition/suit_monitoring/instance/${suitProcess.processInstance.id}/view`"
                  tag="button"
                  class="btn btn-info btn-sm details"
                  data-cy="entityDetailsButton"
                >
                  <font-awesome-icon icon="eye"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                </router-link>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
      <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
    </button>
  </div>
</template>

<script lang="ts" src="./suit-process-list.component.ts"></script>
