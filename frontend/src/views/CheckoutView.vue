<template>
  <div class="checkout-container">
    <h2>Checkout</h2>
    <label><strong>Total cost: {{ price }} kr</strong></label>
    <br /> <br />
    <form class="checkout-form">
      <label for="card-number">Card Number</label>
      <input type="text" id="card-number" placeholder="1234 5678 9012 3456" v-model="cardNumber" required>

      <label for="cardholder-name">Cardholder's Name</label>
      <input type="text" id="cardholder-name" placeholder="John Doe" v-model="cardholderName" required>

      <label>Expiration Date</label>
      <Datepicker id="expiration-date" v-model="selectedDate" inputFormat="MM/yyyy"></Datepicker>

      <label for="cvv">CVV</label>
      <input type="text" id="cvv" placeholder="123" v-model="cvv" required>

      <button class="confirm-purchase-button" :disabled="!canConfirmPurchase" @click.prevent="confirmPurchase">Confirm Purchase</button>
    </form>

    <div v-if="showConfirmation" class="checkout-confirmation">Purchase confirmed! Thank you for your business.</div>
  </div>
</template>

<script>
import { defineComponent } from 'vue'
import Datepicker from 'vue3-datepicker'

export default defineComponent({
  name: 'CheckoutView',
  components: { Datepicker },
  data() {
    return {
      selectedDate: new Date(),
      price: this.$store.state.price,
      confirmation: false,
      cardNumber: '',
      cardholderName: '',
      cvv: '',
    }
  },
  computed: {
    showConfirmation() {
      return this.confirmation;
    },
    canConfirmPurchase() {
      return this.cardNumber.length > 15 && this.cardholderName !== '' && this.cvv.length > 2;
    },
  },
  methods: {
    confirmPurchase() {
      this.confirmation = true
    },
  },
})
</script>

<style>
.checkout-container {
  margin: auto;
  max-width: 500px;
  padding: 20px;
  border: 1px solid #ccc;
}

.checkout-form {
  display: flex;
  flex-direction: column;
  border: 1px solid #ccc;
  padding: 20px;
}

label {
  margin-top: 10px;
  margin-bottom: 5px;
}

input[type="text"],
button {
  padding: 10px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  width: 100%;
  max-width: 100%;
  text-align: center;
}

#expiration-date {
  width: 150px;
}

.confirm-purchase-button {
  background-color: #4CAF50;
  color: white;
  border: none;
}

.confirm-purchase-button:disabled {
  background-color: grey;
}

.confirm-purchase-button:hover {
  background-color: #45a049;
}

.checkout-confirmation {
  color: green;
  margin-top: 10px;
}


.checkout-confirmation {
  margin-top: 20px;
  padding: 10px;
  background-color: #4CAF50;
  color: #fff;
  border-radius: 4px;
}
</style>

