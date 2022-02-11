<template>
  <div>
    <h2 id="page-heading" data-cy="LawyerHeading">
      <span v-text="$t('ppApp.lawyer.home.title')" id="lawyer-heading">Lawyers</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('ppApp.lawyer.home.refreshListLabel')">Refresh List</span>
        </button>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && lawyers && lawyers.length === 0">
      <span v-text="$t('ppApp.lawyer.home.notFound')">No lawyers found</span>
    </div>
    <div class="table-responsive" v-if="lawyers && lawyers.length > 0">
      <table class="table table-striped" aria-describedby="lawyers">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('ppApp.lawyer.name')">Name</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="lawyer in lawyers" :key="lawyer.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'LawyerView', params: { lawyerId: lawyer.id } }">{{ lawyer.id }}</router-link>
            </td>
            <td>{{ lawyer.name }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'LawyerView', params: { lawyerId: lawyer.id } }" custom v-slot="{ navigate }">
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
        ><span id="ppApp.lawyer.delete.question" data-cy="lawyerDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-lawyer-heading" v-text="$t('ppApp.lawyer.delete.question', { id: removeId })">
          Are you sure you want to delete this Lawyer?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-lawyer"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeLawyer()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./lawyer.component.ts"></script>
