<template>
  <nav class="navbar">
    <ul class="navbar-nav">
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" role="button" aria-haspopup="true" aria-expanded="false" @click="toggleLanguageDropdown" id="language">
          {{ t('language') }}
        </a>
        <div class="dropdown-menu" v-if="showLanguageDropdown" @click="hideLanguageDropdown">
          <a class="dropdown-item" href="#" @click.prevent="setLanguage('English')" id="eng">{{ t('english') }}</a>
          <a class="dropdown-item" href="#" @click.prevent="setLanguage('Norwegian')" id="nor">{{ t('norwegian') }}</a>
        </div>
      </li>
      <li class="nav-item">
        <router-link to="/shopping" class="nav-link" id="shopping" exact-active-class="active">{{ t('shopping') }}</router-link>
      </li>
      <li class="nav-item">
        <router-link to="/favorites" class="nav-link" id="favorites" exact-active-class="active">{{ t('favorites') }}</router-link>
      </li>
      <li class="nav-item">
        <router-link to="/cart" class="nav-link" id="cart" exact-active-class="active">{{ t('cart') }}</router-link>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" role="button" aria-haspopup="true" aria-expanded="false" @click="toggleAccountDropdown" id="account">
          {{ t('account') }}
        </a>
        <div class="dropdown-menu" v-if="showAccountDropdown" @click="hideAccountDropdown">
            <button @click="logout" v-if="this.userOnPage.username!==null" to="/register-user" class="logoutButtonForHeader"  >Logout</button>
            <router-link v-else to="/register-user" class="dropdown-item" exact-active-class="active">{{ t('sign-up') }}</router-link>

            <router-link v-if="this.userOnPage.username!==null" to="/login" class="dropdown-item" >My account</router-link>
            <router-link v-else to="/login" class="dropdown-item" >{{ t('log-in') }}</router-link>
        </div>
      </li>
    </ul>
  </nav>
</template>

<script>
import {useI18n} from "vue-i18n";

export default {
  name: 'NavigationBar',
  setup() {
    const { t } = useI18n({
      inheritLocale: true,
      useScope: 'local'
    })
    return { t }
  },
  data() {
    return {
      showLanguageDropdown: false,
      showAccountDropdown: false,
      currentLanguage: 'English',
      user: 'User'
    }
  },
  methods: {
    toggleLanguageDropdown() {
      this.showLanguageDropdown = !this.showLanguageDropdown
    },
    hideLanguageDropdown() {
      this.showLanguageDropdown = false
    },
    toggleAccountDropdown() {
      this.showAccountDropdown = !this.showAccountDropdown
    },
    hideAccountDropdown() {
      this.showAccountDropdown = false
    },
    setLanguage(language) {
      this.currentLanguage = language;
      console.log("Language switched to", language);
      if(this.currentLanguage === 'Norwegian') {
        this.$i18n.locale = 'nb'
      }
      else {
        this.$i18n.locale = 'en'
      }
    },
    logout() {
      console.log("logout");
      this.$store.dispatch("logout");
      this.$router.push("/login"); // redirect to the login page
    },
  },
  mounted() {
    const availableLocales = this.$i18n.availableLocales
    const userLanguage = window.navigator.language
    if (availableLocales.includes(userLanguage)) {
      this.$i18n.locale = userLanguage;
    } else {
      this.$i18n.locale = 'en';
    }
  },
  computed: {
    userOnPage() {
      return this.$store.getters.user;
    },
  },

};
</script>

<style scoped>
body {
  margin: 0;
  padding: 0;
}

.navbar {
  background-color: whitesmoke;
  overflow: hidden;
  font-size: 16px;
  border-bottom: 1px solid #ccc;
  border-radius: 5px;
}

.navbar-nav {
  display: flex;
  justify-content: space-between;
  list-style-type: none;
  margin: 0;
  padding: 0;
}

.nav-item {
  padding: 8px;
}


.nav-link {
  color: #333;
  text-decoration: none;
  cursor: pointer;
}
.nav-link:hover {
  background-color: lightgray;
}

.nav-link.active {
  background-color: darkgray;
  color: #1c1b1b;
}

.dropdown-menu {
  background-color: whitesmoke;
  border: none;
  display: flex;
  flex-direction: column;
}

.dropdown-item {
  color: #333;
  cursor: pointer;
  white-space: normal;
}
.logoutButtonForHeader {
  background: none;
  border: none;
  padding: 0;
  color: #333;
  cursor: pointer;
  font-size: inherit;
  font-family: inherit;
  text-decoration: underline;
}

@media (max-width: 912px) {
  .navbar-nav {
    flex-wrap: wrap;
  }

  .nav-item {
    width: 50%;
    box-sizing: border-box;
  }

  .nav-item:nth-child(odd) {
    padding-right: 0;
  }

  .nav-item:nth-child(even) {
    padding-left: 0;
  }

  .nav-link {
    display: block;
    width: 100%;
    text-align: center;
  }

  .nav-item.dropdown .dropdown-menu {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
  }

  .dropdown-item {
    width: 100%;
    box-sizing: border-box;
    text-align: center;
  }

  .nav-item:nth-child(5) {
    width: 100%;
    padding-left: 8px;
    padding-right: 8px;
    box-sizing: border-box;
  }
}
</style>
