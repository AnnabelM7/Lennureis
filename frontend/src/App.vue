<script setup>
import Header from "./components/Header.vue";
import Footer from "./components/Footer.vue";
import EmailSubscription from './components/EmailSubscription.vue';

</script>

<template>
  <Header />
  <main>
    <router-view />
  </main>
  <EmailSubscription />
  <Footer />
</template>

<style scoped>
main {
  padding: 20px;
  min-height: 80vh;
}
</style>
