<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <div v-if="taskContext.taskInstance">
        <h2 id="page-heading" data-cy="TaskInstanceHeading">
          <span v-text="$t('ppApp.taskInstance.execute.title')" id="task-instance-heading">Task Execution</span>
        </h2>
        <akip-show-task-instance :taskInstance="taskContext.taskInstance">
          <template v-slot:body>
            <hr />
            <div class="form-group">
              <label class="form-control-label" v-text="$t('ppApp.taskDisplayDetails.suitNumber')" for="task-display-details-suitNumber"
                >Suit Number</label
              >
              <input
                type="text"
                class="form-control"
                name="suitNumber"
                id="task-display-details-suitNumber"
                readonly
                data-cy="suitNumber"
                :class="{
                  valid: !$v.taskContext.suitProcess.suit.suitNumber.$invalid,
                  invalid: $v.taskContext.suitProcess.suit.suitNumber.$invalid,
                }"
                v-model="$v.taskContext.suitProcess.suit.suitNumber.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('ppApp.taskDisplayDetails.name')" for="task-display-details-name">Name</label>
              <input
                type="text"
                class="form-control"
                name="name"
                id="task-display-details-name"
                readonly
                data-cy="name"
                :class="{ valid: !$v.taskContext.suitProcess.suit.name.$invalid, invalid: $v.taskContext.suitProcess.suit.name.$invalid }"
                v-model="$v.taskContext.suitProcess.suit.name.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('ppApp.taskDisplayDetails.date')" for="task-display-details-date">Date</label>
              <b-input-group class="mb-3">
                <b-form-input
                  id="task-display-details-date"
                  readonly
                  data-cy="date"
                  type="text"
                  class="form-control"
                  name="date"
                  :class="{ valid: !$v.taskContext.suitProcess.suit.date.$invalid, invalid: $v.taskContext.suitProcess.suit.date.$invalid }"
                  v-model="$v.taskContext.suitProcess.suit.date.$model"
                />
              </b-input-group>
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('ppApp.taskDisplayDetails.claimed')" for="task-display-details-claimed"
                >Claimed</label
              >
              <input
                type="text"
                class="form-control"
                name="claimed"
                id="task-display-details-claimed"
                readonly
                data-cy="claimed"
                :class="{
                  valid: !$v.taskContext.suitProcess.suit.claimed.$invalid,
                  invalid: $v.taskContext.suitProcess.suit.claimed.$invalid,
                }"
                v-model="$v.taskContext.suitProcess.suit.claimed.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('ppApp.taskDisplayDetails.lawyer')" for="task-display-details-lawyer"
                >Lawyer</label
              >
              <input
                type="text"
                class="form-control"
                name="lawyer"
                id="task-display-details-lawyer"
                readonly
                data-cy="lawyer"
                :class="{
                  valid: !$v.taskContext.suitProcess.suit.lawyer.$invalid,
                  invalid: $v.taskContext.suitProcess.suit.lawyer.$invalid,
                }"
                v-model="$v.taskContext.suitProcess.suit.lawyer.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('ppApp.taskDisplayDetails.court')" for="task-display-details-court">Court</label>
              <input
                type="text"
                class="form-control"
                name="court"
                id="task-display-details-court"
                readonly
                data-cy="court"
                :class="{ valid: !$v.taskContext.suitProcess.suit.court.$invalid, invalid: $v.taskContext.suitProcess.suit.court.$invalid }"
                v-model="$v.taskContext.suitProcess.suit.court.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('ppApp.taskDisplayDetails.value')" for="task-display-details-value">Value</label>
              <input
                type="text"
                class="form-control"
                name="value"
                id="task-display-details-value"
                readonly
                data-cy="value"
                :class="{ valid: !$v.taskContext.suitProcess.suit.value.$invalid, invalid: $v.taskContext.suitProcess.suit.value.$invalid }"
                v-model="$v.taskContext.suitProcess.suit.value.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('ppApp.taskDisplayDetails.shouldGroup')" for="task-display-details-shouldGroup"
                >Should Group</label
              >
              <input
                type="checkbox"
                class="form-check"
                name="shouldGroup"
                id="task-display-details-shouldGroup"
                data-cy="shouldGroup"
                :class="{
                  valid: !$v.taskContext.suitProcess.suit.shouldGroup.$invalid,
                  invalid: $v.taskContext.suitProcess.suit.shouldGroup.$invalid,
                }"
                v-model="$v.taskContext.suitProcess.suit.shouldGroup.$model"
              />
            </div>
          </template>
        </akip-show-task-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <button type="submit" v-on:click.prevent="complete()" class="btn btn-success" data-cy="complete">
          <font-awesome-icon icon="check-circle"></font-awesome-icon>&nbsp;Complete
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./task-display-details-execute.component.ts"></script>
