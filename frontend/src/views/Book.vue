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

      <div v-if="seats.length > 0">
        <div v-for="(rowSeats, index) in groupedSeats" :key="index" class="seat-row">
          <div v-for="seat in rowSeats" :key="seat.seatId" class="seat">
            <button
                :class="{
      'seat-available': seat.status === 'Saadaval',
      'seat-booked': seat.status === 'Broneeritud'
    }"
                :disabled="seat.status !== 'Saadaval'"
                class="seat-button"
                @click="selectSeat(seat)"
            >
              {{ seat.seatNumber }}
            </button>

          </div>
        </div>
      </div>

      <p v-else>Istekohti ei ole saadaval.</p>

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
      seats: [],
      adults: 0,
      children: 0,
      selectedSeats: [],
    };
  },
  mounted() {
    const flightId = this.$route.params.id;
    this.getFlightDetails(flightId);
    this.getSeatsByFlight(flightId);
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

    async getSeatsByFlight(flightId) {
      try {
        const response = await axios.get(`http://localhost:8080/seats/flight/${flightId}`);
        this.seats = response.data;
      } catch (error) {
        console.error('Istekohtade hankimise viga', error);
      }
    },

    selectSeat(seat) {
      if (seat.status === 'available') {
        const index = this.selectedSeats.indexOf(seat.seatId);
        if (index === -1) {
          this.selectedSeats.push(seat.seatId);
        } else {
          this.selectedSeats.splice(index, 1);
        }
      }
    },

    formatDate(dateString) {
      const date = new Date(dateString);
      return date.toLocaleDateString();
    },

    bookFlight() {
      alert(`Olete broneerinud lennu sihtkohta: ${this.flight.destination} (${this.flight.departure}) koos valitud istekohtadega: ${this.selectedSeats.join(', ')}!`);
      // TODO: Siin saab hiljem teha päringu broneeringu salvestamiseks andmebaasi vms
    }
  },
  computed: {
    groupedSeats() {
      const rows = [];
      for (let i = 0; i < this.seats.length; i += 6) {
        rows.push(this.seats.slice(i, i + 6));
      }
      return rows;
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

.seat-row {
  display: flex;
  justify-content: center;
  margin-bottom: 10px;
}

.seat {
  margin: 0 5px;
}

.seat-button {
  padding: 10px;
  background-color: #34a853;
  color: white;
  border: none;
  cursor: pointer;
  font-size: 1.1em;
  width: 60px;
  height: 60px;
  text-align: center;
}

.seat-button:disabled {
  background-color: #ccc;
}

.seat-available {
  background-color: #34a853; /* Roheline (saadaval) */
}

.seat-booked {
  background-color: #ccc; /* Hall (broneeritud) */
}

</style>
