<template>
  <footer class="footer">
    <div class="container">
      <div class="row">
        <div class="col-md-6 col-lg-6">
          <h4>FAQ</h4>
          <ul class="list-unstyled">
            <li id="to-faq"><router-link to="/faq">{{ $t('footer-faq') }}</router-link></li>
          </ul>
        </div>
        <div class="col-md-6 col-lg-6">
          <h4>{{ $t('footer-about-us1') }}</h4>
          <ul class="list-unstyled">
            <li id="to-about-us"><router-link to="/about">{{ $t('footer-about-us2') }}</router-link></li>
          </ul>
        </div>
      </div>
    </div>
  </footer>
</template>

<script>
export default {
  name: 'MarketplaceFooter'
}
</script>


<style>
.footer {
  margin: 0;
  background-color: whitesmoke;
  color: #000;
  padding: 50px 0;
  justify-content: center;
  align-items: center;
  border-top: 1px solid #ccc;
  border-radius: 5px;
  box-shadow: 0 50vh 10px 50vh rgba(245, 245, 245, 100);
}

.footer h4 {
  margin-bottom: 20px;
  font-weight: bold;
  text-align: center;
}

.footer ul {
  margin: 0;
  padding: 0;
  list-style: none;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.footer ul li {
  margin-bottom: 10px;
  text-align: center;
}

.footer ul li a {
  color: #000;
}

@media (min-width: 768px) {
  .footer .row {
    display: flex;
    flex-wrap: wrap;
  }

  .footer .col-md-6 {
    width: 50%;
  }
}
</style>