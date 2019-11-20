<template>
  <div id="products-page">
    <div class="page-body">
      <ProductsItem v-for="p in products" :key="p.id" :product="p" @addToBag="addToBag" />
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import ProductsItem from "./ProductsItem";
export default {
  components: { ProductsItem },
  data: function() {
    return {
      products: []
    };
  },
  computed: {
    ...mapGetters(["bagItems", "getToken"])
  },
  mounted: function() {
    const AuthStr = this.getToken;
    this.$http
      .get("http://localhost:9000/api/v1/products", {
        headers: { Authorization: AuthStr }
      })
      .then(r => {
        this.products = r.data;
      });
  },
  methods: {
    addToBag: function(item) {
      this.$store.dispatch("addToCart", item);
    }
  }
};
</script>

<style lang="scss">
$colorPrimary: #ff6c00;
$colorSecondary: #6a1b60;
$colorContrast: #fff;

#products-page {
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
}
</style>
