<template>
  <tr>
    <td>
      <img src="image-icon.jpg" width="50" />
    </td>
    <td>
      <b>Brand</b>
      <br />
      {{ item.name }}
    </td>
    <td>R$ {{ $formatPrice(item.price) }}</td>
    <td>
      <input type="number" v-model="item.quantity" />
    </td>
    <td>R$ {{ $formatPrice(total) }}</td>
    <td>
      <button class="delete-item">Excluir</button>
    </td>
  </tr>
</template>

<script>
export default {
  props: ["item"],
  computed: {
    total: function() {
      return (this.item) ? this.item.price * this.item.quantity : 0;
    }
  },
  // methods: {
    // formatPrice: function(price) {
    //   return price.toFixed(2).replace('.', ',');
    // }
  // }
};
</script>
