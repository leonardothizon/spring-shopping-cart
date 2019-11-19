<template>
  <div class="item">
    <div class="info">
      <img src="image-icon.jpg" />
      <p class="item-brand">Brand</p>
      <p class="item-description">{{ product.name }}</p>
      <p class="item-price">R$ {{ product.price }}</p>
    </div>
    <div class="add-to-cart">
      <a @click="addToBag"><img src="shopping-bag-32.png" width="20" /> <span>Adicionar à sacola</span></a>
    </div>
  </div>
</template>

<script>
export default {
  props: ["product"],
  methods: {
    addToBag: function() {
      this.$store.dispatch('addToCart', this.product)
    }
  }
};
</script>
