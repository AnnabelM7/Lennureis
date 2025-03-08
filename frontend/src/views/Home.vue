<template>
  <div class="home">
    <h1>Tere tulemast CloudReach Airlines lehele!</h1>

    <p>Broneeri oma järgmine lennureis mugavalt:</p>

    <!-- Lennuotsing -->
    <form @submit.prevent="getFlights">
      <label for="departure">Lähtekoht:</label>
      <select v-model="departure" id="departure">
        <option value="TLL">Tallinn (TLL)</option>
        <option value="RIX">Riia (RIX)</option>
        <option value="HEL">Helsingi (HEL)</option>
      </select>

      <label for="destination">Sihtkoht:</label>
      <select v-model="destination" id="destination">
        <option value="LAX">Los Angeles (LAX)</option>
        <option value="JFK">New York (JFK)</option>
        <option value="DXB">Dubai (DXB)</option>
      </select>

      <label for="date">Kuupäev:</label>
      <input type="date" id="date" v-model="date" :min="minDate" required />

      <label for="adults">Täiskasvanud:</label>
      <input type="number" id="adults" v-model="adults" min="1" required />

      <label for="children">Lapsed:</label>
      <input type="number" id="children" v-model="children" min="0" />

      <label for="babies">Beebid:</label>
      <input type="number" id="babies" v-model="babies" min="0" />

      <label for="class">Klass:</label>
      <select id="class" v-model="flightClass">
        <option value="Economy">Turistiklass</option>
        <option value="Business">Äriklass</option>
        <option value="First">1. klass</option>
      </select>

      <label for="currency">Valuuta:</label>
      <select id="currency" v-model="currency">
        <option value="USD">USD</option>
        <option value="EUR">EUR</option>
        <option value="GBP">GBP</option>
      </select>

      <button type="submit">Otsi lende</button>
    </form>

    <!-- Lendude kuvamine -->
    <div v-if="flights && flights.length > 0">
      <h2>Leitud lennud:</h2>
      <ul>
        <li v-for="flight in flights" :key="flight.id">
          <strong>{{ flight.name }}</strong> - {{ flight.price }} {{ currency }}
          ({{ flight.departureTime }} - {{ flight.arrivalTime }})
        </li>
      </ul>
    </div>

  </div>
</template>

<script lang="ts">
import axios from 'axios';

type Flight = {
  id: string;
  name: string;
  price: string;
  departureTime: string;
  arrivalTime: string;
};

export default {
  name: "Home",
  data() {
    return {
      departure: '',
      destination: '',
      date: '',
      adults: 1,
      children: 0,
      babies: 0,
      flightClass: 'Economy',
      currency: 'EUR',
      flights: [] as Flight[],
      minDate: '',
    };
  },
  created() {
    const today = new Date();
    const year = today.getFullYear();
    const month = (today.getMonth() + 1).toString().padStart(2, '0');
    const day = today.getDate().toString().padStart(2, '0');
    this.minDate = `${year}-${month}-${day}`;
  },
  methods: {
    async getFlights() {
      try {
        const response = await axios.get(
            `https://api.flightapi.io/onewaytrip/67c5e4715eeeaccfb6952fa9/${this.departure}/${this.destination}/${this.date}/${this.adults}/${this.children}/${this.babies}/${this.flightClass}/${this.currency}`
        );
        console.log(response.data);
        this.flights = response.data;
      } catch (error) {
        console.error('Error fetching flights:', error);
      }
    }
  }
};
</script>

<style scoped>
.home {
  text-align: center;
  margin-top: 50px;
}
h1 {
  color: #2c3e50;
}

form {
  display: flex;
  flex-direction: column;
  margin-top: 20px;
}

label {
  margin-top: 10px;
}

input, select {
  margin-bottom: 10px;
  padding: 8px;
}

button {
  padding: 10px;
  background-color: #4CAF50;
  color: white;
  border: none;
  cursor: pointer;
}

button:hover {
  background-color: #45a049;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  margin: 5px 0;
}
</style>
