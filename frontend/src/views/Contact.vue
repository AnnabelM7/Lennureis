<template>
  <div class="contact">
    <h1>Kontakt</h1>


    <div class="contact-details">
      <h3>Meie kontaktandmed</h3>
      <p><strong>Telefon:</strong> +372 123 4567</p>
      <p><strong>E-post:</strong> info@cloudreach.com</p>
      <p><strong>Aadress:</strong> Tartu mnt 101, 10112 Tallinn</p>
    </div>


    <!--https://www.embed-map.com/-->
    <iframe
        class="google-map"
        src="https://www.google.com/maps/embed/v1/place?q=Tartu+mnt+101,+10112+Tallinn,+Eesti&key=AIzaSyBFw0Qbyq9zTFTd-tUY6dZWTgaQzuU17R8"
        title="Asukoht: Tartu mnt 101, 10112 Tallinn, Eesti">
    </iframe>


  </div>
</template>
<style scoped>
.contact {
  text-align: center;
  margin-top: 50px;
}

.google-map {
  height: 500px;
  width: 500px;
  border: 0;
  max-width: 100%;
}

</style>