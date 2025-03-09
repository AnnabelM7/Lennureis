<template>
  <!-- Siit edasi ai mõeldud, et mis võiks veel avalehel olla ja mis tekstiga (idee ai poolt, kood nii minu kui ai tehtud)-->
  <div class="about">
    <h3>CloudReach Airlines - Lennud, mis viivad sind kaugemale</h3>
    <p>Meie eesmärk on pakkuda kiireid ja mugavaid lennureise üle kogu maailma. Meie kliendid hindavad meid soodsa
      hinnaga, suurepärase teeninduse ja täpsuse eest.</p>
  </div>

  <div class="newsletter">
    <h3>Liitu meie uudiskirjaga!</h3>
    <p>Saada oma e-mail ja saa teada meie parimatest pakkumistest ja uutest lendudest.</p>

    <input v-model="email" placeholder="Sisesta oma e-mail" type="email"/>
    <button @click="subscribeNewsletter">Liitu</button>

    <p v-if="isSubscribed" class="success-message">Olete edukalt liitunud meie uudiskirjaga!</p>
    <p v-if="email && !isSubscribed && !isValidEmail" class="error-message">Palun sisestage kehtiv e-maili aadress.</p>
  </div>

  <div class="testimonials">
    <h3>Meie kliendid räägivad meist</h3>
    <div class="testimonial-card">
      <p>"Parim lend, mida olen kunagi teinud! Teenindus oli suurepärane ja lend oli täpselt õige." - Annika P.</p>
    </div>
    <div class="testimonial-card">
      <p>"CloudReach Airlines on minu usaldusväärne valik iga kord, kui olen reisile minemas!" - Timo K.</p>
    </div>
  </div>
</template>

<script>
export default {
  name: "EmailSubscription",
  data() {
    return {
      email: "",
      isSubscribed: false,
      isValidEmail: true
    };
  },
  methods: {
    // AI MÕELDUD
    subscribeNewsletter() {
      // E-maili valideerimine
      const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      if (emailPattern.test(this.email)) {
        this.isSubscribed = true; // Näitab teate, et kasutaja on liitunud
        this.isValidEmail = true;
      } else {
        this.isValidEmail = false; // Kui e-mail ei ole kehtiv, kuvatakse viga
      }
    }
  }
};
</script>

<style scoped>
.about {
  text-align: center;
  margin-top: 50px;
}

.newsletter {
  text-align: center;
  background-color: #f1f3f6;
  padding: 30px;
  margin-top: 30px;
  border-radius: 10px;
}

.newsletter h3, about h3 {
  font-size: 1.5em;
}
.about h3 {
  font-size: 1.5em;
}

.newsletter input {
  padding: 12px;
  width: 250px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.newsletter button {
  padding: 12px 25px;
  background-color: #4CAF50;
  color: white;
  border: none;
  cursor: pointer;
  border-radius: 5px;
  margin-top: 10px;
}

.newsletter button:hover {
  background-color: #45a049;
}

.testimonials {
  text-align: center;
  margin-top: 40px;
}

.testimonial-card {
  background-color: #fff;
  padding: 20px;
  margin: 15px;
  box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
  border-radius: 10px;
}

.testimonials h3 {
  font-size: 1.5em;
  margin-bottom: 20px;
}

.testimonial-card p {
  font-style: italic;
  color: #333;
}

.success-message {
  color: green;
  margin-top: 15px;
}

.error-message {
  color: red;
  margin-top: 15px;
}
</style>
