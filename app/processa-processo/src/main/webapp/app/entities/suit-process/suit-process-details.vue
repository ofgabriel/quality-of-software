<template>
  <div class="row justify-content-center">
    <div class="col-10">
      <div v-if="suitProcess.processInstance">
        <h2 class="jh-entity-heading" data-cy="suitProcessDetailsHeading">
          <span v-text="$t('ppApp.suitProcess.detail.title')">SuitProcess</span> {{ suitProcess.id }}
        </h2>
        <akip-show-process-instance :processInstance="suitProcess.processInstance">
          <template v-slot:title> <span v-text="$t('ppApp.suitProcess.detail.title')">SuitProcess</span> #{{ suitProcess.id }} </template>
          <template v-slot:details>
            <div class="card" v-if="suitProcess.suit">
              <h5 class="card-header">
                <span class="title"> Suit #{{ suitProcess.suit.id }} </span>
              </h5>
              <div class="card-body py-0">
                <div class="form-group">
                  <label class="form-control-label" v-text="$t('ppApp.suitProcess.suitNumber')">suitNumber</label>
                  <input
                    readonly
                    type="text"
                    class="form-control"
                    name="suitNumber"
                    id="suit-suitNumber"
                    data-cy="suitNumber"
                    v-model="suitProcess.suit.suitNumber"
                  />
                </div>
              </div>
            </div>
          </template>
        </akip-show-process-instance>
        <br />
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./suit-process-details.component.ts"></script>
