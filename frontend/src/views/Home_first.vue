<template>
  <div class="home">
    <h1>Tere tulemast CloudReach Airlines lehele!</h1>

    <h2>Meie parimad lennupakkumised:</h2>

    <div class="filters">
      <div class="filter-item">
        <label for="destination">Sihtkoht: </label>
        <input v-model="filters.destination" placeholder="Sisesta sihtkoht" type="text"/>
      </div>
      <div class="filter-item">
        <label for="price">Hind (max): </label>
        <input v-model="filters.price" placeholder="Sisesta hind" type="number"/>
      </div>
      <div class="filter-item">
        <label for="sortBy">Sorteeri järgi: </label>
        <select v-model="sortBy">
          <option value="price">Hind</option>
          <option value="date">Kuupäev</option>
          <option value="duration">Lennuaeg</option>
        </select>
      </div>
      <div class="filter-item">
        <button @click="sortAscending = !sortAscending">
          {{ sortAscending ? 'Kasvavalt' : 'Kahanevalt' }}
        </button>
      </div>
    </div>

    <p v-if="filteredAndSortedFlights.length === 0">Praegu pole ühtegi sobivat diili. Palun vaata hiljem uuesti või
      muuda filtreid!</p>

    <table class="flight-table">
      <thead>
      <tr>
        <th>#</th>
        <th>Sihtkoht</th>
        <th>Alguskoht</th>
        <th>Hind (EUR)</th>
        <th>Kuupäev</th>
        <th>Lennuaeg</th>
        <th>Lennufirma</th>
        <th>Broneeri lend</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(flight, index) in filteredAndSortedFlights" :key="index">
        <td>{{ index + 1 }}</td>
        <td>{{ flight.destination }}</td>
        <td>{{ flight.departure }}</td>
        <td>{{ flight.price }}€</td>
        <td>{{ formatDate(flight.date) }}</td>
        <td>{{ flight.duration }}</td>
        <td>{{ flight.airline }}</td>
        <td>
          <router-link :to="`/book/${flight.id}`">
            <button class="book-button">Broneeri</button>
          </router-link>
        </td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>


import axios from "axios";

export default {
  name: "HomeFirst",
  data() {
    return {
      flights: [],
      filters: {
        destination: "",
        price: null,
        date: ""
      },
      sortBy: "price",
      sortAscending: true
    };
  },
  mounted() {
    this.getFlightsFromDb();
  },
  computed: {
    filteredAndSortedFlights() {
      // Alguses on sama mis flights
      let filteredFlights = [...this.flights];

      // Filtreerimine
      filteredFlights = filteredFlights.filter(flight => {
        return (
            (!this.filters.destination || flight.destination.toLowerCase().includes(this.filters.destination.toLowerCase())) &&
            (!this.filters.price || flight.price <= this.filters.price) &&
            (!this.filters.date || flight.date.includes(this.filters.date))
        );
      });

      // Sorteerimine
      return filteredFlights.sort((a, b) => {
        let comparison = 0;
        if (this.sortBy === "price") {
          comparison = a.price - b.price;
        } else if (this.sortBy === "date") {
          comparison = new Date(a.date) - new Date(b.date);
        } else if (this.sortBy === "duration") {
          const aDuration = this.durationToMinutes(a.duration);
          const bDuration = this.durationToMinutes(b.duration);
          comparison = aDuration - bDuration;
        }

        return this.sortAscending ? comparison : -comparison;
      });
    }

  },
  methods: {
    formatDate(dateString) {
      const date = new Date(dateString);
      return date.toLocaleDateString();
    },
    durationToMinutes(duration) {
      const [hours, minutes] = duration.split("h").map(str => str.trim());
      return parseInt(hours) * 60 + (parseInt(minutes) || 0);
    },
    async getFlightsFromDb() {
      try {
        const response = await axios.get("http://localhost:8080/flights/from-db");
        console.log("API vastus: ", response.data);
        this.flights = response.data;
      } catch (error) {
        console.error("Error fetching flights from DB:", error);
      }
    },

  },
};
</script>

<style scoped>
.home {
  text-align: center;
  margin-top: 50px;
}

h1, h2 {
  color: #2c3e50;
}

.filters {
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
  margin: 20px 0;
}

.filter-item {
  margin: 10px;
  text-align: center;
}

input, select {
  margin-bottom: 10px;
  padding: 8px;
  font-size: 1em;
  width: 180px;
  box-sizing: border-box;
}

button {
  padding: 10px;
  background-color: #034d14;
  color: white;
  border: none;
  cursor: pointer;
  font-size: 1em;
  width: 180px;
  box-sizing: border-box;
}

button:hover {
  background-color: #45a049;
}

.flight-table {
  width: 80%;
  margin: 20px auto;
  border-collapse: collapse;
  text-align: center;
  font-family: Arial, sans-serif;
}

.flight-table th, .flight-table td {
  padding: 10px;
  border: 1px solid #ddd;
}

.flight-table thead {
  background-color: #f2f2f2;
  font-weight: bold;
}

.flight-table tbody tr:nth-child(even) {
  background-color: #f9f9f9;
}

.flight-table tbody tr:hover {
  background-color: #f1f1f1;
  transition: 0.3s;
}

</style>
