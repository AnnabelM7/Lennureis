<template>
  <div class="home">
    <h1>Tere tulemast CloudReach Airlines lehele!</h1>

    <p>Otsi oma järgmine lennureis mugavalt:</p>

    <!-- Lennuotsing -->
    <form class="flight-form" @submit.prevent="getFlights">
      <label for="airport">Lähtekoht (lennujaam):</label>
      <select id="airport" v-model="airport">
        <option value="TLL">Tallinn (TLL)</option>
        <option value="RIX">Riia (RIX)</option>
        <option value="HEL">Helsingi (HEL)</option>
        <option value="FRA">Frankfurt (FRA)</option>
        <option value="AMS">Amsterdam (AMS)</option>
        <option value="BER">Berliin (BER)</option>
        <option value="CPH">Kopenhaagen (CPH)</option>
      </select>

      <label for="type">Lennu tüüp:</label>
      <select id="type" v-model="type">
        <option value="departure">Väljumine</option>
        <option value="arrival">Saabumine</option>
      </select>

      <label for="date">Kuupäev:</label>
      <input id="date" v-model="date" :min="minDate" required type="date"/>

      <label for="numberOfFlights">Mitu lendu soovite näha:</label>
      <input id="numberOfFlights" v-model="numberOfFlights" max="100" min="1" required type="number"/>

      <button type="submit">Otsi lende</button>
    </form>

    <!-- Lendude kuvamine -->
    <h2 v-if="flights.length">Leitud lennud:</h2>
    <table v-if="flights.length" class="flight-table">
      <thead>
      <tr>
        <th>#</th>
        <th>Lennufirma</th>
        <th>Lennuki Tüüp</th>
        <th>Väljumisvärav</th>
        <th>Sihtkoht (IATA Kood)</th>
        <th>Saabumivärav</th>
        <th>Broneeri lend</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(flight, index) in flights" :key="flight.flight_iata">
        <td>{{ index + 1 }}</td>
        <td class="capitalize">{{ flight.airline.name || 'Ei ole määratud' }}</td>
        <td class="capitalize">{{ flight.aircraft.modelText || 'Ei ole määratud' }}</td>
        <td>{{ flight.departure.gate || 'Ei ole määratud' }}</td>
        <td class="capitalize">{{ flight.arrival.iataCode || 'Ei ole määratud' }}</td>
        <td>{{ flight.arrival.gate || 'Ei ole määratud' }}</td>

        <td>
          <button class="book-button" @click="showBookingAlert()">
            Broneeri
          </button>
        </td>

      </tr>
      </tbody>
    </table>

  </div>

</template>

<script lang="ts">
import axios from 'axios';

interface Flight {
  flight_iata: string;
  airline: {
    name: string;
  };
  departure: {
    gate?: string;
  };
  arrival: {
    gate?: string;
    iataCode?: string;
  };
  aircraft: {
    modelText: string;
  };
}


export default {
  name: "Home",
  data() {
    return {
      numberOfFlights: 10,
      airport: '',
      date: '',
      currency: 'EUR',
      flights: [] as Flight[],
      minDate: '',
      apiKey: "c95b18b6d7e5868d81f04795eaa68688",
      //: import.meta.env.FLIGHT_API_KEY, Ei saa variablena seda kätte
      type: 'departure',
    };
  },
  created() {
    const today = new Date();
    today.setDate(today.getDate() + 8);  // Lisame 8 päeva sest varasemaid api ei näita

    const year = today.getFullYear();
    const month = (today.getMonth() + 1).toString().padStart(2, '0');
    const day = today.getDate().toString().padStart(2, '0');
    this.minDate = `${year}-${month}-${day}`;
  },


  methods: {
    async getFlights() {
      try {
        // Loome päringu URL koos parameetritega
        const url1 = `https://api.aviationstack.com/v1/flightsFuture`;
        const params = {
          access_key: this.apiKey,
          iataCode: this.airport,
          type: this.type,
          date: this.date,
        };
        console.log(this.date);

        console.log('Making API request to:', url1);
        console.log('With parameters:', params);
        const response = await axios.get(url1, {params});

        this.flights = response.data.data.slice(0, this.numberOfFlights);
      } catch (error) {
        console.error('Error fetching flights:', error);
      }
    },
    showBookingAlert() {
      const message = `Kui soovite seda lendu broneerida, siis võtke ühendust CloudReach Airlines'iga e-posti aadressil: info@cloudreach.com `;
      alert(message);
    },
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

input, select, button {
  margin-bottom: 10px;
  padding: 15px;
  font-size: 1em;
  width: 100%;
  max-width: 280px;
  box-sizing: border-box;
}

button {
  padding: 20px;
  background-color: #4CAF50;
  color: white;
  border: none;
  cursor: pointer;
  max-width: 280px;
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

.flight-form {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  max-width: 300px;
  margin: 0 auto;
}

.flight-table {
  border-collapse: collapse;
  text-align: center;
  font-family: Arial, sans-serif;
  width: 80%;
  max-width: 1000px;
  margin: 0 auto;
}

.flight-table th,
.flight-table td {
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

.capitalize {
  text-transform: capitalize;
}

</style>
