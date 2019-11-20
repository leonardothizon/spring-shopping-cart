<template>
  <div id="login-page">
    <div class="logo">
      <img src="/logo.png" />
    </div>
    <div class="login-form">
      <div class="form-content">
        <div class="login-form-input">
          <p class="error" v-show="error">{{ error }}</p>
          <input type="text" placeholder="USUÁRIO" v-model="login" />
          <input type="password" placeholder="SENHA" v-model="password" />
        </div>
        <div class="login-form-actions">
          <a href>Esqueci minha senha</a>
          <button @click="authenticate">Acessar</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data: function() {
    return {
      login: "",
      password: "",
      error: undefined
    };
  },
  methods: {
    authenticate: function() {
      let plainClientCredentials = this.login + ":" + this.password;
      let base64ClientCredentials = new String(btoa(plainClientCredentials));
      let AuthStr = "Basic " + base64ClientCredentials;
      this.$http
        .post("http://localhost:9000/api/v1/user/auth", this.login, {
          headers: { "Content-Type": "text/plain", Authorization: AuthStr }
        })
        .then(
          r => {
            let user = r.data;
            this.$store.dispatch("setUser", { user: user, token: AuthStr });
            this.$router.push("home");
          },
          () => {
            this.error = "Usuário ou senha inválidos";
          }
        );
    }
  }
};
</script>

<style scoped lang="scss">
$colorPrimary: #ff6c00;
$colorSecondary: #6a1b60;
$colorContrast: #fff;
#login-page {
  display: flex;
  align-items: center;
  justify-content: center;
  background-image: url("/login-bg.png");
  background-repeat: no-repeat;
  height: 100%;
  .logo {
    position: absolute;
    align-self: flex-start;
    left: 40px;
    top: 40px;
  }
  .login-form {
    width: 500px;
    border-radius: 5px;
    padding: 50px 0;
    background-color: rgba(255, 255, 255, 0.7);
    border: 1px solid $colorPrimary;
    display: flex;
    align-items: center;
    justify-content: center;
    .form-content {
      display: flex;
      flex-direction: column;
      width: 80%;
      padding: 20px 0;
    }
    .login-form-input {
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      input {
        width: 100%;
        background-color: #fff;
        border-radius: 5px;
        padding: 10px 20px;
        border: 1px solid $colorPrimary;
        margin: 10px 0;
        color: $colorPrimary;
      }
    }
    .login-form-actions {
      width: 100%;
      display: flex;
      justify-content: space-between;
      a {
        color: $colorPrimary;
        text-decoration: none;
        display: flex;
        align-self: center;
        &:hover {
          text-decoration: underline;
        }
      }
      button {
        padding: 10px 15px;
        background-color: $colorPrimary;
        color: $colorContrast;
        border-radius: 5px;
        border: none;
        cursor: pointer;
      }
    }
  }
}
</style>
