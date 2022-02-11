<template>
  <div>
    <h2 id="page-heading" data-cy="GroupHeading">
      <span v-text="$t('ppApp.group.home.title')" id="group-heading">Groups</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('ppApp.group.home.refreshListLabel')">Refresh List</span>
        </button>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && groups && groups.length === 0">
      <span v-text="$t('ppApp.group.home.notFound')">No groups found</span>
    </div>
    <div class="table-responsive" v-if="groups && groups.length > 0">
      <table class="table table-striped" aria-describedby="groups">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('ppApp.group.name')">Name</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="group in groups" :key="group.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'GroupView', params: { groupId: group.id } }">{{ group.id }}</router-link>
            </td>
            <td>{{ group.name }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'GroupView', params: { groupId: group.id } }" custom v-slot="{ navigate }">
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
        ><span id="ppApp.group.delete.question" data-cy="groupDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-group-heading" v-text="$t('ppApp.group.delete.question', { id: removeId })">
          Are you sure you want to delete this Group?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-group"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeGroup()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./group.component.ts"></script>
