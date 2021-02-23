<template>
  <v-container>
    <v-row justify="center" row>
      <v-col style="width: 70%">
        <v-list>
          <v-list-item
              v-for="user in $store.state.App.users" :key="user.id"
          >
            <v-list-item-action>
              <v-btn
                  fab
                  small
                  depressed
                  color="primary"
              >
                {{ user.id }}
              </v-btn>
            </v-list-item-action>

            <v-list-item-content>
              <v-list-item-title>
                User Database Record <strong>ID {{ user.id }}</strong>
              </v-list-item-title>
            </v-list-item-content>
            <v-list-item-content>
              <div style="color: red" align="center" v-if="user.status === 'NOT_ACTIVATED'">
                NOT ACTIVATED!
              </div>
            </v-list-item-content>

            <v-list-item-action>
              <v-row>
                <v-btn style="margin-right: 2em" outlined text :disabled="user.status === 'ACTIVATED'">
                  ACTIVATED
                </v-btn>
                <v-btn style="margin-right: 2em" outlined text :disabled="user.status === 'BANNED'">
                  BANNED
                </v-btn>

              </v-row>
            </v-list-item-action>
          </v-list-item>

        </v-list>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  name: "user"
}
</script>

<style scoped>

</style>