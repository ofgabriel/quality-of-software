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
              <label
                class="form-control-label"
                v-text="$t('ppApp.taskWatcherDefinition.shouldReceiveNotification')"
                for="task-watcher-definition-shouldReceiveNotification"
                >Should Receive Notification</label
              >
              <input
                type="checkbox"
                class="form-check"
                name="shouldReceiveNotification"
                id="task-watcher-definition-shouldReceiveNotification"
                data-cy="shouldReceiveNotification"
                :class="{
                  valid: !$v.taskContext.suitProcess.suit.shouldReceiveNotification.$invalid,
                  invalid: $v.taskContext.suitProcess.suit.shouldReceiveNotification.$invalid,
                }"
                v-model="$v.taskContext.suitProcess.suit.shouldReceiveNotification.$model"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('ppApp.taskWatcherDefinition.watcher')" for="task-watcher-definition-watcher"
                >Watcher</label
              >
              <select
                class="form-control"
                id="task-watcher-definition-watcher"
                data-cy="watcher"
                name="watcher"
                v-model="taskContext.suitProcess.suit.watcher"
                required
              >
                <option v-if="!taskContext.suitProcess.suit.watcher" v-bind:value="null" selected></option>
                <option
                  v-bind:value="
                    taskContext.suitProcess.suit.watcher && lawyerOption.id === taskContext.suitProcess.suit.watcher.id
                      ? taskContext.suitProcess.suit.watcher
                      : lawyerOption
                  "
                  v-for="lawyerOption in lawyers"
                  :key="lawyerOption.id"
                >
                  {{ lawyerOption.name }}
                </option>
              </select>
            </div>
            <div v-if="$v.taskContext.suitProcess.suit.watcher.$anyDirty && $v.taskContext.suitProcess.suit.watcher.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.taskContext.suitProcess.suit.watcher.required"
                v-text="$t('entity.validation.required')"
              >
                This field is required.
              </small>
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

<script lang="ts" src="./task-watcher-definition-execute.component.ts"></script>
