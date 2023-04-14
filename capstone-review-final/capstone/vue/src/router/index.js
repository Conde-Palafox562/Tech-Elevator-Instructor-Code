import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import CreateTruck from '../views/CreateTruck.vue'
import ListOfTrucks from "../views/ListOfTrucks.vue"
import Order from "../views/Order.vue"
import Profile from "../views/Profile.vue"
import ViewTruck from "../views/ViewTruck.vue"
import AddMenu from '../views/AddMenu.vue'
import AddSubMenu from '../views/AddSubMenu.vue'
import AddMenuItem from '../views/AddMenuItem.vue'

Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/trucks",
      name: "trucks",
      component: ListOfTrucks,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/trucks/:id",
      name: "truck-details",
      component: ViewTruck,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/new-truck",
      name: "new-truck",
      component: CreateTruck,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/profile",
      name: "profile",
      component: Profile,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/order",
      name: "order",
      component: Order,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/trucks/:truckId/menu",
      name: "create-menu",
      component: AddMenu,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/trucks/:truckId/menu/:menuId",
      name: "create-submenu",
      component: AddSubMenu,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/trucks/:truckId/menu/:menuId/submenu/:subMenuId",
      name: "create-menuitem",
      component: AddMenuItem,
      meta: {
        requiresAuth: true
      }
    }
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
