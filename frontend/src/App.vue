<template>
  <header>
    <NavigationBar />
  </header>
  <router-view />
  <footer><MarketplaceFooter />
  </footer>
</template>

<script>
import NavigationBar from "@/components/NavigationBar";
import MarketplaceFooter from "@/components/MarketplaceFooter"
export default {
  components: {NavigationBar, MarketplaceFooter}
}

</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
p {
  font-family: Serif;
}
</style>