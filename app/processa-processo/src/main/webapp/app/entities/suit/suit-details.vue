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
          <dt>
            <span v-text="$t('ppApp.suit.name')">Name</span>
          </dt>
          <dd>
            <span>{{ suit.name }}</span>
          </dd>
          <dt>
            <span v-text="$t('ppApp.suit.date')">Date</span>
          </dt>
          <dd>
            <span>{{ suit.date }}</span>
          </dd>
          <dt>
            <span v-text="$t('ppApp.suit.claimed')">Claimed</span>
          </dt>
          <dd>
            <span>{{ suit.claimed }}</span>
          </dd>
          <dt>
            <span v-text="$t('ppApp.suit.lawyer')">Lawyer</span>
          </dt>
          <dd>
            <span>{{ suit.lawyer }}</span>
          </dd>
          <dt>
            <span v-text="$t('ppApp.suit.court')">Court</span>
          </dt>
          <dd>
            <span>{{ suit.court }}</span>
          </dd>
          <dt>
            <span v-text="$t('ppApp.suit.value')">Value</span>
          </dt>
          <dd>
            <span>{{ suit.value }}</span>
          </dd>
          <dt>
            <span v-text="$t('ppApp.suit.shouldGroup')">Should Group</span>
          </dt>
          <dd>
            <span>{{ suit.shouldGroup }}</span>
          </dd>
          <dt>
            <span v-text="$t('ppApp.suit.watcher')">Watcher</span>
          </dt>
          <dd>
            <div v-if="suit.watcher">
              <router-link :to="{ name: 'LawyerView', params: { LawyerId: suit.watcher.id } }">{{ suit.watcher.name }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="$t('ppApp.suit.group')">Group</span>
          </dt>
          <dd>
            <div v-if="suit.group">
              <router-link :to="{ name: 'GroupView', params: { GroupId: suit.group.id } }">{{ suit.group.name }}</router-link>
            </div>
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
