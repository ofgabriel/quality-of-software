<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="suit">
        <h2 class="jh-entity-heading" data-cy="suitDetailsHeading">
          <span v-text="$t('ppApp.suit.detail.title')">Suit</span> {{ suit.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('ppApp.suit.suitNumber')">Suit Number</span>
          </dt>
          <dd>
            <span>{{ suit.suitNumber }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./suit-details.component.ts"></script>
