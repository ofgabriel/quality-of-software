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
              <label class="form-control-label" v-text="$t('ppApp.taskSuitRegistering.suitNumber')" for="task-suit-registering-suitNumber"
                >Suit Number</label
              >
              <input
                type="text"
                class="form-control"
                name="suitNumber"
                id="task-suit-registering-suitNumber"
                data-cy="suitNumber"
                :class="{
                  valid: !$v.taskContext.suitProcess.suit.suitNumber.$invalid,
                  invalid: $v.taskContext.suitProcess.suit.suitNumber.$invalid,
                }"
                v-model="$v.taskContext.suitProcess.suit.suitNumber.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('ppApp.taskSuitRegistering.name')" for="task-suit-registering-name">Name</label>
              <input
                type="text"
                class="form-control"
                name="name"
                id="task-suit-registering-name"
                data-cy="name"
                :class="{ valid: !$v.taskContext.suitProcess.suit.name.$invalid, invalid: $v.taskContext.suitProcess.suit.name.$invalid }"
                v-model="$v.taskContext.suitProcess.suit.name.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('ppApp.taskSuitRegistering.date')" for="task-suit-registering-date">Date</label>
              <b-input-group class="mb-3">
                <b-input-group-prepend>
                  <b-form-datepicker
                    aria-controls="task-suit-registering-date"
                    v-model="$v.taskContext.suitProcess.suit.date.$model"
                    name="date"
                    class="form-control"
                    :locale="currentLanguage"
                    button-only
                    today-button
                    reset-button
                    close-button
                  >
                  </b-form-datepicker>
                </b-input-group-prepend>

                <b-form-input
                  id="task-suit-registering-date"
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
              <label class="form-control-label" v-text="$t('ppApp.taskSuitRegistering.claimed')" for="task-suit-registering-claimed"
                >Claimed</label
              >
              <input
                type="text"
                class="form-control"
                name="claimed"
                id="task-suit-registering-claimed"
                data-cy="claimed"
                :class="{
                  valid: !$v.taskContext.suitProcess.suit.claimed.$invalid,
                  invalid: $v.taskContext.suitProcess.suit.claimed.$invalid,
                }"
                v-model="$v.taskContext.suitProcess.suit.claimed.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('ppApp.taskSuitRegistering.lawyer')" for="task-suit-registering-lawyer"
                >Lawyer</label
              >
              <input
                type="text"
                class="form-control"
                name="lawyer"
                id="task-suit-registering-lawyer"
                data-cy="lawyer"
                :class="{
                  valid: !$v.taskContext.suitProcess.suit.lawyer.$invalid,
                  invalid: $v.taskContext.suitProcess.suit.lawyer.$invalid,
                }"
                v-model="$v.taskContext.suitProcess.suit.lawyer.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('ppApp.taskSuitRegistering.court')" for="task-suit-registering-court"
                >Court</label
              >
              <input
                type="text"
                class="form-control"
                name="court"
                id="task-suit-registering-court"
                data-cy="court"
                :class="{ valid: !$v.taskContext.suitProcess.suit.court.$invalid, invalid: $v.taskContext.suitProcess.suit.court.$invalid }"
                v-model="$v.taskContext.suitProcess.suit.court.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('ppApp.taskSuitRegistering.value')" for="task-suit-registering-value"
                >Value</label
              >
              <input
                type="text"
                class="form-control"
                name="value"
                id="task-suit-registering-value"
                data-cy="value"
                :class="{ valid: !$v.taskContext.suitProcess.suit.value.$invalid, invalid: $v.taskContext.suitProcess.suit.value.$invalid }"
                v-model="$v.taskContext.suitProcess.suit.value.$model"
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

<script lang="ts" src="./task-suit-registering-execute.component.ts"></script>
