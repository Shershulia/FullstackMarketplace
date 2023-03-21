<template>
  <nav class="navbar">
    <ul class="navbar-nav">
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" role="button" aria-haspopup="true" aria-expanded="false" @click="toggleLanguageDropdown" id="language">
          {{ t('language') }}
        </a>
        <div class="dropdown-menu" v-if="showLanguageDropdown" @click="hideLanguageDropdown">
          <a class="dropdown-item" href="#" @click.prevent="setLanguage('English')">{{ t('english') }}</a>
          <a class="dropdown-item" href="#" @click.prevent="setLanguage('Norwegian')">{{ t('norwegian') }}</a>
        </div>
      </li>
      <li class="nav-item">
        <router-link to="/shopping" class="nav-link" id="shopping">{{ t('shopping') }}</router-link>
      </li>
      <li class="nav-item">
        <router-link to="/favorites" class="nav-link" id="favorites">{{ t('favorites') }}</router-link>
      </li>
      <li class="nav-item">
        <router-link to="/cart" class="nav-link" id="cart">{{ t('cart') }}</router-link>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" role="button" aria-haspopup="true" aria-expanded="false" @click="toggleAccountDropdown" id="account">
          {{ t('account') }}
        </a>
        <div class="dropdown-menu" v-if="showAccountDropdown" @click="hideAccountDropdown">
          <a class="dropdown-item" href="#">{{ t('sign-up') }}</a>
          <a class="dropdown-item" href="#">{{ t('log-in') }}</a>
        </div>
      </li>
    </ul>
  </nav>
</template>

<script>
import {useI18n} from "vue-i18n";
import i18n from "@/i18n";

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
        this.$i18n.locale = 'no'
      }
      else {
        this.$i18n.locale = 'en'
      }
    }
  }
}
</script>

<style>
.navbar {
  background-color: whitesmoke;
  overflow: hidden;
  font-size: 16px;
  border: 1px solid #ccc;
  border-radius: 5px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
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

</style>
