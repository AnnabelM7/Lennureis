<template>
  <div class="booking">
    <h1>Broneeri lend</h1>

    <div v-if="flight">
      <h2>Lennu info</h2>
      <p><strong>Sihtkoht:</strong> {{ flight.destination }}</p>
      <p><strong>Alguskoht:</strong> {{ flight.departure }}</p>
      <p><strong>Hind:</strong> {{ flight.price }} EUR</p>
      <p><strong>Kuupäev:</strong> {{ formatDate(flight.date) }}</p>
      <p><strong>Lennuaeg:</strong> {{ flight.duration }}</p>
      <p><strong>Lennufirma:</strong> {{ flight.airline || 'Ei ole määratud' }}</p>
      <h2>Reisijad:</h2>

      <div>
        <label for="adults">Täiskasvanud:</label>
        <input id="adults" v-model="adults" min="0" type="number"/>
      </div>

      <div>
        <label for="children">Lapsed:</label>
        <input id="children" v-model="children" min="0" type="number"/>
      </div>

      <h2>Vali endale istekohad:</h2>


      <button class="book-button" @click="bookFlight">Broneeri</button>
    </div>

    <p v-else>Laadimine...</p>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: 'Book',
  data() {
    return {
      flight: null,
    };
  },
  mounted() {
    const flightId = this.$route.params.id;
    this.getFlightDetails(flightId);
  },
  methods: {
    async getFlightDetails(flightId) {
      try {
        const response = await axios.get(`http://localhost:8080/flights/${flightId}`);
        this.flight = response.data;
      } catch (error) {
        console.error('Lennu detailide hankimise viga', error);
      }
    },

    formatDate(dateString) {
      const date = new Date(dateString);
      return date.toLocaleDateString();
    },

    bookFlight() {
      alert(`Olete broneerinud lennu sihtkohta: ${this.flight.destination} (${this.flight.departure})!`);
      //todo Siin saab hiljem teha päringu broneeringu salvestamiseks andmebaasi vms
    }
  }
};
</script>

<style scoped>
.booking {
  text-align: center;
  margin-top: 30px;
}

h1 {
  color: #2c3e50;
}

h2 {
  color: #34495e;
}

p {
  font-size: 1.2em;
  color: #555;
}

strong {
  font-weight: bold;
}

.book-button {
  padding: 12px 20px;
  background-color: #034d14;
  color: white;
  border: none;
  cursor: pointer;
  font-size: 1.2em;
  margin-top: 20px;
  width: 200px;
}

.book-button:hover {
  background-color: #45a049;
}
</style>
