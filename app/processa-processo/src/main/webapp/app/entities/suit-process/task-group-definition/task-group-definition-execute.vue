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
              <label class="form-control-label" v-text="$t('ppApp.taskGroupDefinition.group')" for="task-group-definition-group"
                >Group</label
              >
              <select
                class="form-control"
                id="task-group-definition-group"
                data-cy="group"
                name="group"
                v-model="taskContext.suitProcess.suit.group"
                required
              >
                <option v-if="!taskContext.suitProcess.suit.group" v-bind:value="null" selected></option>
                <option
                  v-bind:value="
                    taskContext.suitProcess.suit.group && groupOption.id === taskContext.suitProcess.suit.group.id
                      ? taskContext.suitProcess.suit.group
                      : groupOption
                  "
                  v-for="groupOption in groups"
                  :key="groupOption.id"
                >
                  {{ groupOption.name }}
                </option>
              </select>
            </div>
            <div v-if="$v.taskContext.suitProcess.suit.group.$anyDirty && $v.taskContext.suitProcess.suit.group.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.taskContext.suitProcess.suit.group.required"
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

<script lang="ts" src="./task-group-definition-execute.component.ts"></script>
