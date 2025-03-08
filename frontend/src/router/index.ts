import { createRouter, createWebHistory } from "vue-router";
import Home from "../views/Home.vue";
import Contact from "../views/Contact.vue";

const routes = [
    { path: "/", component: Home, meta: { title: "Avaleht" } },
    { path: "/contact", component: Contact, meta: { title: "Kontakt" } },
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
