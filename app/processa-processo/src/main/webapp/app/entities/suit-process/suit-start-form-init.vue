<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="ppApp.suitStartForm.home.createOrEditLabel"
          data-cy="SuitStartFormCreateUpdateHeading"
          v-text="$t('ppApp.suitStartForm.home.createOrEditLabel')"
        >
          Create or edit a SuitStartForm
        </h2>
        <div v-if="suitProcess.processInstance">
          <akip-show-process-definition :processDefinition="suitProcess.processInstance.processDefinition">
            <template v-slot:body>
              <hr />
              <div v-if="suitProcess.suit">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('ppApp.suitStartForm.suitNumber')" for="suit-start-form-suitNumber"
                    >Suit Number</label
                  >
                  <input
                    type="text"
                    class="form-control"
                    name="suitNumber"
                    id="suit-start-form-suitNumber"
                    data-cy="suitNumber"
                    :class="{ valid: !$v.suitProcess.suit.suitNumber.$invalid, invalid: $v.suitProcess.suit.suitNumber.$invalid }"
                    v-model="$v.suitProcess.suit.suitNumber.$model"
                  />
                </div>
              </div>
            </template>
          </akip-show-process-definition>
          <br />
          <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.suitProcess.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="play"></font-awesome-icon>&nbsp;<span>Start</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./suit-start-form-init.component.ts"></script>
