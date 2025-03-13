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
      <div class="passenger-info">
        <div>
          <label for="adults">Täiskasvanud: </label>
          <input id="adults" v-model="adults" min="1" type="number"/>
        </div>

        <div>
          <label for="children">Lapsed: </label>
          <input id="children" v-model="children" min="0" type="number"/>
        </div>
      </div>

      <h3>Lisasoovid:</h3>
      <div class="checkboxes">
        <div>
          <input id="window" v-model="preferences.windowSeat" type="checkbox"/>
          <label for="window">Eelistan võimalusel, et istmed on akna all</label>
        </div>

        <div>
          <input id="middle" v-model="preferences.middleSeat" type="checkbox"/>
          <label for="middle">Eelistan võimalusel, et istmed on keskel</label>
        </div>

        <div>
          <input id="aisle" v-model="preferences.aisleSeat" type="checkbox"/>
          <label for="aisle">Eelistan võimalusel, et istmed on ääres</label>
        </div>

        <div>
          <input id="extraLegroom" v-model="preferences.extraLegroom" type="checkbox"/>
          <label for="extraLegroom">Eelistan võimalusel ekstra jalaruum istmetel</label>
        </div>
      </div>

      <div class="buttons">
        <button class="back-button" @click="goBack">Tagasi</button>
        <button class="book-button" @click="goToNextStep">Edasi</button>
      </div>
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
      seats: [],
      adults: 1,
      children: 0,
      preferences: {
        windowSeat: false,
        extraLegroom: false,
        aisleSeat: false,
        middleSeat: false,
      },
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

    goToNextStep() {
      this.$router.push({
        name: 'seat-selection',
        query: {
          flightId: this.flight.id,
          adults: this.adults,
          children: this.children,
          preferences: JSON.stringify(this.preferences),
        },
      });
    },

    goBack() {
      this.$router.push('/');
    }
  },
};
</script>

<style scoped>
.booking {
  text-align: center;
  margin-top: 30px;
}

h1, h2, h3 {
  color: #2c3e50;
}


p {
  font-size: 1.2em;
  color: #555;
}

strong {
  font-weight: bold;
}

.book-button, .back-button {
  padding: 12px 20px;
  background-color: #034d14;
  color: white;
  border: none;
  cursor: pointer;
  font-size: 1.2em;
  margin-top: 20px;
  width: 200px;
}

.buttons {
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
}

.book-button:hover {
  background-color: #45a049;
}

.back-button {
  background-color: #aaa;
}

.back-button:hover {
  background-color: #888;
}

.passenger-info {
  margin-bottom: 20px;
}

.passenger-info div {
  margin-bottom: 10px;
}

input[type="number"] {
  padding: 15px;
  font-size: 1em;
  width: 100%;
  max-width: 280px;
  box-sizing: border-box;
  margin-bottom: 15px;
  border: 1px solid #ddd;
  border-radius: 8px;
  transition: all 0.3s ease-in-out;
}

input[type="number"]:focus {
  outline: none;
  border-color: #34a853;
}

.checkboxes {
  margin-top: 20px;
  text-align: center;
}

.checkboxes div {
  margin-bottom: 10px;
}

input[type="checkbox"] {
  margin-right: 10px;
}
</style>
