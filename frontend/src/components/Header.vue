<template>
  <header>
    <nav>
      <ul>
        <li><a href="/">Avaleht</a></li>
        <li><a href="/otsi">Otsi reisi</a></li>
        <li><a href="/contact">Kontakt</a></li>
      </ul>
    </nav>
  </header>
</template>

<style scoped>
* {
  box-sizing: border-box;
}

html, body {
  width: 100%;
  height: 100%;
}

header {
  background: #034d14;
  color: white;
  padding: 25px;
  display: flex;
  justify-content: center;
  align-items: center;
  position: sticky;
  top: 0;
  width: 100%;
  z-index: 1000;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

nav ul {
  list-style: none;
  display: flex;
  gap: 25px;
  justify-content: center;
  flex-grow: 1;
  width: 100%;
}

nav a {
  color: white;
  text-decoration: none;
  font-size: 20px;
  font-weight: bold;
}

ul {
  list-style: none;
  display: flex;
  gap: 15px;
  margin: 0;
  padding: 0;
}

ul li {
  display: inline;
  text-align: center;
}
</style>
<script setup lang="ts">
</script>