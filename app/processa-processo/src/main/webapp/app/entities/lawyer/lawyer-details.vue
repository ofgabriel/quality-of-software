<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="lawyer">
        <h2 class="jh-entity-heading" data-cy="lawyerDetailsHeading">
          <span v-text="$t('ppApp.lawyer.detail.title')">Lawyer</span> {{ lawyer.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('ppApp.lawyer.name')">Name</span>
          </dt>
          <dd>
            <span>{{ lawyer.name }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./lawyer-details.component.ts"></script>
