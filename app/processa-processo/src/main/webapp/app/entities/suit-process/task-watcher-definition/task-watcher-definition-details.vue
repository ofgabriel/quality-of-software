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
              <label class="form-control-label" v-text="$t('ppApp.taskWatcherDefinition.shouldReceiveNotification')"
                >shouldReceiveNotification</label
              >
              <input
                readonly
                type="text"
                class="form-control"
                name="shouldReceiveNotification"
                id="suit-shouldReceiveNotification"
                data-cy="shouldReceiveNotification"
                v-model="taskContext.suitProcess.suit.shouldReceiveNotification"
              />
            </div>
            <div class="form-group">
              <label class="form-control-label" v-text="$t('ppApp.taskWatcherDefinition.watcher')" for="task-watcher-definition-watcher"
                >Watcher</label
              >
              <input
                v-if="taskContext.suitProcess.suit.watcher"
                readonly
                type="text"
                class="form-control"
                name="watcher"
                id="suit-watcher"
                data-cy="watcher"
                :value="taskContext.suitProcess.suit.watcher.name"
              />
              <input v-else readonly type="text" class="form-control" name="watcher" id="suit-watcher" data-cy="watcher" value="" />
            </div>
          </template>
        </akip-show-task-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>

        <router-link
          v-if="taskContext.taskInstance.status == 'NEW' || taskContext.taskInstance.status == 'ASSIGNED'"
          :to="`/process-definition/suit_monitoring/task/watcher_definition/${taskContext.taskInstance.id}/execute`"
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

<script lang="ts" src="./task-watcher-definition-details.component.ts"></script>
