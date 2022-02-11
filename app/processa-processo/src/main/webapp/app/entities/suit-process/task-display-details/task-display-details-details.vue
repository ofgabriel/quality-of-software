<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <h2 id="page-heading" data-cy="TaskInstanceHeading">
        <span v-text="$t('ppApp.taskInstance.details.title')" id="task-instance-heading">Task Details</span>
      </h2>
      <div v-if="taskContext.taskInstance">
        <akip-show-task-instance :taskInstance="taskContext.taskInstance">
          <template v-slot:body>
            <hr />
            <div class="form-group">
              <label class="form-control-label" v-text="$t('ppApp.taskDisplayDetails.suitNumber')">suitNumber</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="suitNumber"
                id="suit-suitNumber"
                data-cy="suitNumber"
                v-model="taskContext.suitProcess.suit.suitNumber"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('ppApp.taskDisplayDetails.name')">name</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="name"
                id="suit-name"
                data-cy="name"
                v-model="taskContext.suitProcess.suit.name"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('ppApp.taskDisplayDetails.date')">date</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="date"
                id="suit-date"
                data-cy="date"
                v-model="taskContext.suitProcess.suit.date"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('ppApp.taskDisplayDetails.claimed')">claimed</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="claimed"
                id="suit-claimed"
                data-cy="claimed"
                v-model="taskContext.suitProcess.suit.claimed"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('ppApp.taskDisplayDetails.lawyer')">lawyer</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="lawyer"
                id="suit-lawyer"
                data-cy="lawyer"
                v-model="taskContext.suitProcess.suit.lawyer"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('ppApp.taskDisplayDetails.court')">court</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="court"
                id="suit-court"
                data-cy="court"
                v-model="taskContext.suitProcess.suit.court"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('ppApp.taskDisplayDetails.value')">value</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="value"
                id="suit-value"
                data-cy="value"
                v-model="taskContext.suitProcess.suit.value"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('ppApp.taskDisplayDetails.shouldGroup')">shouldGroup</label>
              <input
                readonly
                type="text"
                class="form-control"
                name="shouldGroup"
                id="suit-shouldGroup"
                data-cy="shouldGroup"
                v-model="taskContext.suitProcess.suit.shouldGroup"
              />
            </div>
          </template>
        </akip-show-task-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>

        <router-link
          v-if="taskContext.taskInstance.status == 'NEW' || taskContext.taskInstance.status == 'ASSIGNED'"
          :to="`/process-definition/suit_monitoring/task/display_suit_details/${taskContext.taskInstance.id}/execute`"
          tag="button"
          class="btn btn-primary"
          data-cy="entityDetailsButton"
        >
          <font-awesome-icon icon="play"></font-awesome-icon>&nbsp;Execute
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./task-display-details-details.component.ts"></script>
