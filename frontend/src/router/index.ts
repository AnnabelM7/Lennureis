import {createRouter, createWebHistory} from "vue-router";
import Home from "../views/Home.vue";
import Contact from "../views/Contact.vue";
import FlightDetails from '../views/FlightDetails.vue';
import HomeFirst from '../views/Home_first.vue';

const routes = [
    {path: "/", component: HomeFirst, meta: {title: "Avaleht"}},
    {path: "/otsi", component: Home, meta: {title: "Avaleht2"}}, //todo
    {path: "/contact", component: Contact, meta: {title: "Kontakt"}},
    {path: '/',name: 'Home',component: Home},{path: '/flight/:id',name: 'FlightDetails', component: FlightDetails,props: true}
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});


router.afterEach((to) => {
    if (typeof to.meta.title === "string") {
        document.title = to.meta.title;
    } else {
        document.title = "Minu Rakendus"; // Vaikeväärtus
    }
});

export default router;
