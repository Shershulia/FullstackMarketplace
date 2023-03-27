<template>
  <div class="faq-page">
    <h1>{{ $t('faq-head') }}</h1>
    <div class="faq-item">
      <h2>{{ $t('faq-question1') }}</h2>
      <p>{{ $t('faq-ans1') }}</p>
    </div>
    <div class="faq-item">
      <h2>{{ $t('faq-question2') }}</h2>
      <p>{{ $t('faq-ans2') }}</p>
    </div>
    <div class="faq-item">
      <h2>{{ $t('faq-question3') }}</h2>
      <p>{{ $t('faq-ans3') }}</p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'FaqView',
}
</script>

<style>
.faq-page {
  background-color: white;
  padding: 20px;
}

.faq-item {
  margin-bottom: 20px;
}

.faq-item h2 {
  margin-bottom: 10px;
}

.faq-item p {
  font-size: 16px;
}
</style>
