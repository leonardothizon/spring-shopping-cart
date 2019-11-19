<template>
  <div id="products-page">
    <div class="page-header">
      <div class="header-content">
        <img class="logo" src="/logo.png" />
        <nav>
          <a href>INÍCIO</a>
          <a href>PRODUTOS</a>
        </nav>
      </div>
      <div class="header-content">
        <nav>
          <a>Olá, Leonardo</a>
          <a @click="$router.push('shopping-bag')"> <img src="shopping-bag-128.png" width="32" /> {{ bagItems.length }} </a>
        </nav>
      </div>
    </div>
    <div class="page-body">
      <ProductsItem v-for="p in products" :key="p.id" :product="p" />
    </div>
    <div class="page-footer">
      <p>Bella Face Loja de Cosméticos FAKE</p>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import ProductsItem from "./ProductsItem";
export default {
  components: { ProductsItem },
  data: function() {
    return {
      products: []
    };
  },
  computed: {
    ...mapGetters(['bagItems'])
  },
  mounted: function() {
    // const USER_TOKEN = "bGVvbmFyZG86bGVvbmFyZG8=";
    const AuthStr = "Basic bGVvbmFyZG86bGVvbmFyZG8=";
    this.$http
      .get("http://localhost:9000/api/v1/products", {
        headers: { Authorization: AuthStr }
      })
      .then(r => {
        this.products = r.data;
      });
  }
};
</script>

<style lang="scss">
$colorPrimary: #ff6c00;
$colorSecondary: #6a1b60;
$colorContrast: #fff;

#products-page {
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
        &:hover {
          text-decoration: underline;
        }
      }
    }
  }

  .page-body {
    display: flex;
    flex-flow: wrap;
    padding: 20px;
    max-width: 1100px;
    margin: auto;
    justify-content: center;
    .break {
      flex-basis: 100%;
      height: 0;
    }
    .item {
      display: flex;
      flex-direction: column;
      border: 2px solid $colorPrimary;
      margin: 10px;
      width: 230px;
      border-radius: 5px;
      .info {
        display: flex;
        align-self: flex-start;
        flex-direction: column;
        margin: 10px auto;
        img {
          margin: 10px 0;
        }
        .item-brand {
          font-size: 12px;
          font-weight: bold;
        }
        .item-description {
          font-size: 14px;
        }
        .item-price {
          color: $colorPrimary;
        }
      }
      .add-to-cart {
        width: 100%;
        background-color: $colorPrimary;
        color: $colorContrast;
        align-self: flex-end;
        display: flex;
        cursor: pointer;
        a {
          width: 100%;
          display: flex;
          align-items: center;
          padding: 5px 0;
          justify-content: space-evenly;
          position: relative;
          color: $colorContrast;
          text-decoration: none;
        }
        img {
          position: absolute;
          left: 5px;
        }
        span {
          display: flex;
          justify-self: center;
        }
      }
    }
  }

  .page-footer {
    border-top: 3px solid $colorPrimary;
    color: $colorPrimary;
  }
}
</style>
