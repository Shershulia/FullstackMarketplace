<template>
  <NavigationBar />
  <div class="about-us">
    <h1>{{ t('about-us1') }}</h1>
    <p>{{ t('about-us2') }}</p>
    <h2>{{ t('about-us3') }}</h2>
    <ul>
      <li><strong>{{ t('about-us4') }}</strong>{{ t('about-us5') }}</li>
      <li><strong>{{ t('about-us6') }}</strong> {{ t('about-us7') }}</li>
      <li><strong>{{ t('about-us8') }}</strong>{{ t('about-us9') }}</li>
    </ul>
    <h2>{{ t('about-us10') }}</h2>
    <p>{{ t('about-us11') }}</p>
    <h2>{{ t('about-us12') }}</h2>
    <p>{{ t('about-us13') }}</p>
  </div>
</template>

<script>
import {useI18n} from "vue-i18n";

export default {
  setup() {
    const { t } = useI18n({
      inheritLocale: true,
      useScope: 'local'
    })
    return { t }
  },
  name: 'AboutUs'
}
</script>

<style>
.about-us {
  max-width: 800px;
  margin: 0 auto;
  padding: 50px 20px;
  text-align: justify;
}
.about-us h1, .about-us h2 {
  text-align: center;
  margin-bottom: 20px;
}
.about-us ul {
  list-style: none;
  margin: 0;
  padding: 0;
}
.about-us li {
  margin-bottom: 10px;
}
</style>




