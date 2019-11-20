<template>
  <div id="app">
    <div class="page-header" v-if="authenticated">
      <div class="header-content">
        <img class="logo" src="/logo.png" />
        <nav>
          <a @click="$router.push('home')">INÍCIO</a>
          <a @click="$router.push('products')">PRODUTOS</a>
        </nav>
      </div>
      <div class="header-content">
        <nav>
          <a @click="logout">Olá, {{ user.firstName }}</a>
          <a @click="$router.push('shopping-bag')">
            <img src="shopping-bag-128.png" width="32" />
            {{ bagItems.length }}
          </a>
        </nav>
      </div>
    </div>
    <router-view></router-view>
    <div class="page-footer" v-if="authenticated">
      <p>Bella Face Loja de Cosméticos FAKE</p>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
export default {
  name: "app",
  computed: {
    ...mapGetters(["bagItems", "user"]),
    authenticated: function() {
      return this.user && this.user.id;
    }
  },
  created: function() {
    if (!this.authenticated) this.$router.push('/');
  },
  methods: {
    logout: function() {
      this.$store.dispatch("logout");
      this.$router.push('/');
    }
  }
};
</script>

<style lang="scss">
html,
body {
  height: 100%;
}
* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  height: 100%;
}
$colorPrimary: #ff6c00;
$colorSecondary: #ff9140;
$colorContrast: #fff;

.page-header {
  width: 100%;
  height: 100px;
  background-color: $colorPrimary;
  display: flex;
  justify-content: space-between;
  padding: 0 10px;

  .header-content {
    display: flex;
    align-items: center;
    justify-content: space-evenly;
    .logo {
      width: 80px;
    }
    nav a {
      margin: 0 20px;
      color: $colorContrast;
      text-decoration: none;
      cursor: pointer;
      &:hover {
        text-decoration: underline;
      }
    }
  }
}
.page-footer {
  border-top: 3px solid $colorPrimary;
  color: $colorPrimary;
}
</style>
