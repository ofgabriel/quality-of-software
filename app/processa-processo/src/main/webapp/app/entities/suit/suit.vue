<template>
  <div>
    <h2 id="page-heading" data-cy="SuitHeading">
      <span v-text="$t('ppApp.suit.home.title')" id="suit-heading">Suits</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('ppApp.suit.home.refreshListLabel')">Refresh List</span>
        </button>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && suits && suits.length === 0">
      <span v-text="$t('ppApp.suit.home.notFound')">No suits found</span>
    </div>
    <div class="table-responsive" v-if="suits && suits.length > 0">
      <table class="table table-striped" aria-describedby="suits">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('ppApp.suit.suitNumber')">Suit Number</span></th>
            <th scope="row"><span v-text="$t('ppApp.suit.name')">Name</span></th>
            <th scope="row"><span v-text="$t('ppApp.suit.date')">Date</span></th>
            <th scope="row"><span v-text="$t('ppApp.suit.claimed')">Claimed</span></th>
            <th scope="row"><span v-text="$t('ppApp.suit.lawyer')">Lawyer</span></th>
            <th scope="row"><span v-text="$t('ppApp.suit.court')">Court</span></th>
            <th scope="row"><span v-text="$t('ppApp.suit.value')">Value</span></th>
            <th scope="row"><span v-text="$t('ppApp.suit.shouldGroup')">Should Group</span></th>
            <th scope="row"><span v-text="$t('ppApp.suit.watcher')">Watcher</span></th>
            <th scope="row"><span v-text="$t('ppApp.suit.group')">Group</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="suit in suits" :key="suit.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'SuitView', params: { suitId: suit.id } }">{{ suit.id }}</router-link>
            </td>
            <td>{{ suit.suitNumber }}</td>
            <td>{{ suit.name }}</td>
            <td>{{ suit.date }}</td>
            <td>{{ suit.claimed }}</td>
            <td>{{ suit.lawyer }}</td>
            <td>{{ suit.court }}</td>
            <td>{{ suit.value }}</td>
            <td>{{ suit.shouldGroup }}</td>
            <td>
              <div v-if="suit.watcher">
                <router-link :to="{ name: 'LawyerView', params: { LawyerId: suit.watcher.id } }">{{ suit.watcher.name }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="suit.group">
                <router-link :to="{ name: 'GroupView', params: { GroupId: suit.group.id } }">{{ suit.group.name }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'SuitView', params: { suitId: suit.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span id="ppApp.suit.delete.question" data-cy="suitDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-suit-heading" v-text="$t('ppApp.suit.delete.question', { id: removeId })">
          Are you sure you want to delete this Suit?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-suit"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeSuit()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./suit.component.ts"></script>
