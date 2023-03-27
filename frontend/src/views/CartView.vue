<template>
  <div class="container">
    <h1 class="title">Shopping Cart</h1>
    <p><strong>Your favorite items are just a few clicks away from you.</strong></p>
    <div class="cart">
      <div v-for="(item, index) in createdCart" :key="index" class="cart-item">
        <img :src="item.image" alt="product image" class="product-image" />
        <div class="product-info">
          <h2 class="product-name">{{ item.name }}</h2>
          <p class="product-price">{{ item.productPrice }} kr</p>
        </div>
        <button @click="removeFromCart(index)" class="remove-button">Remove</button>
      </div>
      <div v-if="cartItems.length === 0" class="empty-cart">
        Your cart is empty
      </div>
    </div>
    <div class="checkout">
      <p><strong>Total: {{ totalPrice }} kr</strong></p>
      <button class="checkout-button" @click="$router.push('/checkout'); changePrice();">Proceed to checkout</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      cartItems: [
        {
          name: "Product 1",
          image: "https://i.pinimg.com/736x/bd/c9/83/bdc9832e5f32ee6168f10536549551bc--kids-bedroom-ideas-girls-bedroom.jpg",
          price: "19.99 kr",
        },
        {
          name: "Gaming chair for beginnerrrrrrrrrrrrrrrrrrrrrr",
          image: "https://i.pinimg.com/736x/bd/c9/83/bdc9832e5f32ee6168f10536549551bc--kids-bedroom-ideas-girls-bedroom.jpg",
          price: "29.99 kr",
        },
        {
          name: "Product 3333333333333 33333333333",
          image: "https://i.redd.it/ksqjj3d3ikp51.jpg",
          price: "39.99 kr",
        },
      ],
    };
  },
  methods: {
    removeFromCart(index) {
      this.$store.state.cart.splice(index, 1);
    },
    changePrice() {
      this.$store.commit("setPrice", this.$store.state.cart.reduce((total, item) => total + parseFloat(item.productPrice), 0).toFixed(2))
      console.log(this.$store.state.price)
    },
  },
  computed: {
    totalPrice() {
      return this.$store.state.cart.reduce((total, item) => total + parseFloat(item.productPrice), 0).toFixed(2);
    },
    createdCart() {
      return this.$store.state.cart;
    }
  },
};
</script>


<style scoped>
img {
  max-width: 60%;
  max-height: 60%;
}

.container {
  background-color: #fff;
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  align-items: center;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.title {
  font-size: 36px;
  margin-bottom: 20px;
}

.cart {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
  justify-content: center;
}

.cart-item {
  display: flex;
  align-items: center;
  gap: 20px;
  padding: 25px;
  border: 1px solid #ccc;
  border-radius: 5px;
  max-width: 400px;
}

.product-name {
  font-size: 18px;
  font-weight: bold;
  max-width: 100%;
  margin-bottom: 5px;
  overflow-wrap: break-word;
  word-wrap: break-word;
  hyphens: auto;
}

.product-info {
  flex: 1 1 50%;
  min-width: 30%;
}

.product-price {
  padding-bottom: 10px;
  margin-top: 10px;
  margin-bottom: 20px;
}


.checkout-button {
  background-color: #3a883d;
  color: #fff;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  width: 30%;
}

.checkout-button:hover {
  background-color: #449f48;
}

.remove-button {
  background-color: #910000;
  color: white;
  padding: 10px;
  border-radius: 5px;
  border: none;
  cursor: pointer;
  font-size: 16px;
}

.remove-button:hover {
  background-color: #ab0101;

}


@media (max-width: 468px) {
  .cart-item {
    flex-basis: calc(100% - 40px);
    max-width: none;
    padding-right: 50px;
    position: relative;
  }

  .product-info {
    flex-basis: 70%;
  }
  .remove-button {
    position: absolute;
    bottom: 5px;
    left: 42.5%;
    width: 40%;
    height: 40px;
    margin: 0 0 0 -45px; /* negative margin of half the button's width */
  }
}

</style>