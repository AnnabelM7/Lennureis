import {createRouter, createWebHistory} from "vue-router";
import Home from "../views/FindFlights.vue";
import Contact from "../views/Contact.vue";
import HomeFirst from '../views/Home.vue';
import Book from '../views/Book.vue';
import Seat_selection from "../views/SeatSelection.vue";


const routes = [
    {path: '/book/:id', name: 'Book', component: Book},
    {path: "/", component: HomeFirst, meta: {title: "Avaleht"}},
    {path: "/otsi", component: Home, meta: {title: "Avaleht2"}},
    {path: "/contact", component: Contact, meta: {title: "Kontakt"}},
    {path: '/seat-selection', name: 'seat-selection', component: Seat_selection,},
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
