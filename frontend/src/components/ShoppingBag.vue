<template>
  <div id="shopping-bag-page">
    <div class="page-body">
      <h2>Sacola de compras: {{ bagItems.length }} itens</h2>
      <table>
        <tr>
          <th></th>
          <th>Item</th>
          <th>Unitário</th>
          <th>Quantidade</th>
          <th>Total</th>
          <th></th>
        </tr>
        <ShoppingBagItem
          v-for="item in bagItems"
          :key="item.id"
          :item="item"
          @removeItem="removeItem(item)"
        ></ShoppingBagItem>
      </table>
      <div class="order-observations">
        <label>Observação da compra</label>
        <textarea v-model="orderComments"></textarea>
      </div>
      <div class="order-total">
        <p class="total-label">TOTAL</p>
        <p>R$ {{ orderTotal }}</p>
      </div>
      <button class="finalize-order" @click="finalizeOrder">Finalizar pedido</button>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import ShoppingBagItem from "./ShoppingBagItem";
export default {
  components: { ShoppingBagItem },
  data: function() {
    return {
      orderComments: ""
    };
  },
  computed: {
    ...mapGetters(["bagItems", "getToken"]),
    orderTotal: function() {
      let total = 0;
      for (let item of this.bagItems) {
        total += item.price * item.quantity;
      }
      return this.$formatPrice(total);
    }
  },
  methods: {
    removeItem: function(item) {
      this.$store.dispatch("removeFromCart", item.id);
    },
    finalizeOrder: function() {
      if (!confirm('Deseja finalizar o pedido?')) return;
      let items = [];
      for (let item of this.bagItems) {
        let orderItem = {
          product: item,
          quantity: item.quantity,
          unityPrice: item.price
        };
        items.push(orderItem);
      }
      let order = {
        comments: this.orderComments,
        customer: { id: 1 },
        items: items
      };
      const AuthStr = this.getToken;
      this.$http
        .post("http://localhost:9000/api/v1/orders", order, {
          headers: { "Content-Type": "application/json", Authorization: AuthStr }
        })
        .then((r) => {
          let pedidoId = r.data.id;
          alert("Pedido " + pedidoId +" gerado!");
          this.$store.dispatch("cleanShoppingBag");
          this.$router.push('home');
        }).catch(function (error) {
          if (error && error.response.data.message) {
            alert(error.response.data.message);
          } else {
            alert('Erro ao inserir pedido');
          }
        });
    }
  }
};
</script>

<style lang="scss">
$colorPrimary: #ff6c00;
$colorSecondary: #ff9140;
$colorContrast: #fff;

// .page-header {
//   width: 100%;
//   height: 100px;
//   background-color: $colorPrimary;
//   display: flex;
//   justify-content: space-between;
//   padding: 0 10px;

//   .header-content {
//     display: flex;
//     align-items: center;
//     justify-content: space-evenly;
//     .logo {
//       width: 80px;
//     }
//     nav a {
//       margin: 0 20px;
//       color: $colorContrast;
//       text-decoration: none;
//       &:hover {
//         text-decoration: underline;
//       }
//     }
//   }
// }

.page-body {
  display: flex;
  flex-flow: wrap;
  padding: 20px;
  max-width: 1100px;
  margin: auto;
  justify-content: center;
  flex-direction: column;
  h2 {
    text-align: right;
    margin: 10px 0;
  }
  table {
    border: 1px solid #ccc;
    border-collapse: collapse;
    margin: 0;
    padding: 0;
    width: 100%;
    table-layout: fixed;
    input {
      text-align: center;
      padding: 3px;
      max-width: 100px;
      border-radius: 3px;
      border: 1px solid #ccc;
      outline: none;
      &:focus {
        border: 1px solid $colorPrimary;
      }
    }
    .delete-item {
      background-color: #fc3f4d;
      padding: 3px 10px;
      border: none;
      color: #fff;
      border-radius: 3px;
      cursor: pointer;
      &:hover {
        background-color: #f80012;
      }
    }
  }

  table caption {
    font-size: 1.5em;
    margin: 0.5em 0 0.75em;
  }

  table tr {
    background-color: #f8f8f8;
    border: 1px solid #ddd;
    padding: 0.35em;
  }

  table th,
  table td {
    padding: 0.625em;
    text-align: center;
  }

  table th {
    font-size: 0.85em;
    letter-spacing: 0.1em;
    text-transform: uppercase;
  }

  @media screen and (max-width: 600px) {
    table {
      border: 0;
    }

    table caption {
      font-size: 1.3em;
    }

    table thead {
      border: none;
      clip: rect(0 0 0 0);
      height: 1px;
      margin: -1px;
      overflow: hidden;
      padding: 0;
      position: absolute;
      width: 1px;
    }

    table tr {
      border-bottom: 3px solid #ddd;
      display: block;
      margin-bottom: 0.625em;
    }

    table td {
      border-bottom: 1px solid #ddd;
      display: block;
      font-size: 0.8em;
      text-align: right;
    }

    table td::before {
      /*
    * aria-label has no advantage, it won't be read inside a table
    content: attr(aria-label);
    */
      content: attr(data-label);
      float: left;
      font-weight: bold;
      text-transform: uppercase;
    }

    table td:last-child {
      border-bottom: 0;
    }
  }
  .order-observations {
    margin: 10px 0;
    width: 100%;
    label {
      font-size: 14px;
      justify-self: flex-start;
    }
    textarea {
      resize: none;
      width: 100%;
      height: 50px;
      outline: none;
      border-radius: 3px;
      border: 1px solid #ccc;
      &:focus {
        border: 1px solid $colorPrimary;
      }
    }
  }
  .order-total {
    font-weight: bold;
    font-size: 18px;
    .total-label {
      color: $colorPrimary;
      font-size: 14px;
      margin: 5px 0;
    }
    padding: 20px;
    border: 1px solid $colorPrimary;
    border-radius: 5px;
    margin: 10px 0;
  }
  .finalize-order {
    padding: 10px 20px;
    background-color: $colorPrimary;
    color: $colorContrast;
    border-radius: 5px;
    border: none;
    cursor: pointer;
    text-transform: uppercase;
    &:hover {
      background-color: $colorSecondary;
    }
  }
}

// .page-footer {
//   border-top: 3px solid $colorPrimary;
//   color: $colorPrimary;
// }
</style>
