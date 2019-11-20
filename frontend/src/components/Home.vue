<template>
  <div class="page-body">
    <a class="shortcut" @click="$router.push('products')">Produtos</a>
    <a class="shortcut">Meus Dados</a>
    <a class="shortcut">Configurações</a>
  </div>
</template>

<script>
export default {
  data: function() {
    return {
      products: []
    };
  }
};
</script>

<style lang="scss">
$colorPrimary: #ff6c00;
$colorSecondary: #6a1b60;
$colorContrast: #fff;

.page-body {
  display: flex;
  flex-flow: wrap;
  padding: 20px;
  max-width: 900px;
  margin: auto;
  justify-content: center;
  .shortcut {
    display: flex;
    cursor: pointer;
    margin: 10px 0;
    padding: 10px 30px;
    font-size: 28px;
    background-color: $colorContrast;
    color: $colorPrimary;
    border: 3px solid $colorPrimary;
    border-radius: 5px;
    &:hover {
      background-color: $colorPrimary;
      color: $colorContrast;
    }
  }
}
</style>
