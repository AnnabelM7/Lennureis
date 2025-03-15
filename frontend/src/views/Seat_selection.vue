<template>
  <div class="seat-selection">
    <h1>Istmete valik</h1>

    <div class="summary">
      <p><strong>Täiskasvanud:</strong> {{ adults }}</p>
      <p><strong>Lapsed:</strong> {{ children }}</p>
      <p><strong>Valitud eelistused:</strong></p>
      <ul v-if="hasPreferences">
        <li v-if="preferences.windowSeat">Istmed on akna all</li>
        <li v-if="preferences.middleSeat">Istmed on keskel</li>
        <li v-if="preferences.aisleSeat">Istmed on ääres</li>
        <li v-if="preferences.extraLegroom">Ekstra jalaruum istmetel</li>
      </ul>
      <p v-else>Ühtegi eelistust ei ole valitud.</p>
    </div>

    <h2>Istekohtade valik:</h2>

    <ul class="seats-description">
      <li>Helehalliga on märgitud juba broneeritud istmed.</li>
      <li v-if="hasPreferences">Kollasega on märgitud eelistatud istmed.</li>
    </ul>


    <div v-if="seats.length === 0">
      <p>Istekohti ei ole saadaval.</p>
    </div>

    <div class="seat-grid">
      <div v-for="seat in seats" :key="seat.id" class="seat">
        <button
            :class="{
            'seat-available': seat.status === 'Saadaval',
            'seat-booked': seat.status === 'Broneeritud',
            'seat-suggested': isSuggested(seat)
          }"
            :disabled="seat.status === 'Broneeritud' || isSelectedLimitReached"
            @click="selectSeat(seat)"
        >
          {{ seat.seatNumber }}
        </button>
      </div>
    </div>

    <div v-if="selectedSeats.length > 0">
      <h3>Valitud istmed:</h3>
      <ul>
        <li v-for="(seat, index) in selectedSeats" :key="seat.id">
          {{ index + 1 }}. iste on: {{ seat.seatNumber }}
        </li>
      </ul>
    </div>

    <button v-if="selectedSeats.length > 0" class="reset-button" @click="resetSelection">Tühista valitud istmed</button>

    <div class="buttons">
      <button class="back-button" @click="goBack">Tagasi</button>
      <button class="book-button" @click="bookSeats">Broneeri istmed</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: 'SeatSelectionPage',
  data() {
    return {
      adults: parseInt(this.$route.query.adults || 0),
      children: parseInt(this.$route.query.children || 0),
      preferences: JSON.parse(this.$route.query.preferences || '{}'),
      seats: [],
      selectedSeats: []
    };
  },
  mounted() {
    this.getSeatsByFlight();
  },
  computed: {
    resetSelection() {
      this.selectedSeats = [];
    },
    hasPreferences() {
      return Object.values(this.preferences).includes(true);
    },
    totalSeatsToSelect() {
      return this.adults + this.children;
    },
    isSelectedLimitReached() {
      return this.selectedSeats.length >= this.totalSeatsToSelect;
    }
  },
  methods: {
    async getSeatsByFlight() {
      const flightId = this.$route.query.flightId;
      try {
        const response = await axios.get(`http://localhost:8080/seats/flight/${flightId}`);
        this.seats = response.data;
        console.log("Kõik istme andmed:", this.seats);
      } catch (error) {
        console.error('Istekohtade hankimise viga', error);
      }
    },
    selectSeat(seat) {
      if (seat.status !== 'Broneeritud' && !this.isSelectedLimitReached) {
        const index = this.selectedSeats.findIndex(s => s.id === seat.id);
        if (index === -1) {
          this.selectedSeats.push(seat);
        } else {
          this.selectedSeats.splice(index, 1);
        }
      }
    },
    isSuggested(seat) {
      if (seat.status === 'Broneeritud') {
        return false;
      }
      console.log('Kontrollin istet:', seat);
      if (this.preferences.windowSeat && seat.seatType === 'Akna ääres') {
        console.log('Soovitus: Akna ääres');
        return true;
      } else if (this.preferences.middleSeat && seat.seatType === 'Keskel') {
        console.log('Soovitus: Keskel');
        return true;
      } else if (this.preferences.aisleSeat && seat.seatType === 'Ääres') {
        console.log('Soovitus: Ääres');
        return true;
      } else if (this.preferences.extraLegroom && seat.extraLegRoom) {
        console.log('Soovitus: Ekstra jalaruumiga');
        return true;
      }
      console.log('Ei');
      return false;
    },

    goBack() {
      this.$router.push('/');
    },
    bookSeats() {
      alert('Istmed on edukalt broneeritud! Aitäh, et valisite CloudReach Airlines-i. Peagi suuname teid tagasi avalehele.');
      this.$router.push('/');
    },
  },
};
</script>


<style scoped>
.seat-selection {
  text-align: center;
  margin-top: 30px;
}

h1, h2 {
  color: #2c3e50;
}

.summary {
  margin-bottom: 30px;
  font-size: 1.2em;
  color: #555;
}

ul {
  list-style-type: none;
  padding: 0;
}

ul li {
  font-size: 1.1em;
  color: #555;
}

.seat-grid {
  display: grid;
  grid-template-columns: repeat(6, 1fr);
  gap: 5px;
  margin-bottom: 20px;
}

.seat {
  display: flex;
  justify-content: center;
}

.seat button {
  padding: 15px;
  background-color: #f2f2f2;
  border: 1px solid #ddd;
  border-radius: 8px;
  font-size: 1.2em;
  cursor: pointer;
  transition: all 0.3s ease-in-out;
  width: 50px;
  height: 50px;
}

.seat button:hover {
  background-color: #e6e6e6;
}

.buttons {
  display: flex;
  justify-content: space-between;
  margin-top: 30px;
}

.book-button, .back-button {
  padding: 12px 20px;
  background-color: #034d14;
  color: white;
  border: none;
  cursor: pointer;
  font-size: 1.2em;
  width: 200px;
}

.reset-button {
  padding: 12px 15px;
  background-color: #aaa;
  color: white;
  border: none;
  cursor: pointer;
  font-size: 1em;
  width: 250px;
}

.book-button:hover, .back-button:hover {
  background-color: #45a049;
}

.back-button {
  background-color: #aaa;
}

.back-button:hover {
  background-color: #888;
}

.seat-suggested {
  border: 2px solid #fbc02d !important;
}
</style>