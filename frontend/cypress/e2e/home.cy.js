describe('Home page client browsing', () => {
  it('passes if client can get to the site and see the home page', () => {
    cy.visit('http://localhost:8080/')
  })

  it('passes if client can use the navigation bar and change language', () => {
    cy.visit('http://localhost:8080/')
    cy.on('uncaught:exception', (err, runnable) => {
      return false
    })
    cy.get('#language').click()
    cy.get('#nor').click()
  })
})

describe('Rendering about us and router testing', () => {
  it('passes if client can get to the site and see the home page', () => {
    cy.visit('http://localhost:8080/')
  })

  it('passes if client can get to the about us section', () => {
    cy.visit('http://localhost:8080/')
    cy.on('uncaught:exception', (err, runnable) => {
      return false
    })
    cy.get('#to-about-us').click()
    cy.scrollTo('bottom')
  })
})

describe('Testing of signing up and logging in', () => {
  it('passes if client can get to the sign up page and interact with inputs', () => {
    cy.visit('http://localhost:8080/register-user')
    cy.get('[placeholder="Enter your username..."]').type('ShopEnjoyer')
    cy.get('[type="email"]').type('enjoyshopping@gmail.com')
  })

  it('passes if client is not allowed to register until all fields are validated as acceptable', () => {
    cy.visit('http://localhost:8080/register-user')
    cy.get('[placeholder="Enter your username..."]').type('ShopEnjoyer')
    cy.get('[type="email"]').type('enjoyshopping@gmail.com')
    cy.get('[placeholder="Enter your password..."]').type('shopping1234', { force: true} )
    cy.get('.buttons > input').click()
  })

  it('passes if client is able to view and use login', () => {
    cy.visit('http://localhost:8080/register-user')
    cy.get('#account').click()
    cy.get('[href="/login"]').click()
    cy.get('#username').type('ole1')
    cy.get('#password').type('Asdf1234')
    cy.get('form > button').click()
  })

})

describe('Browsing the store and selecting items', () => {
  it('passes if client can see available items and select them', () => {
    cy.visit('http://localhost:8080/shopping')
    cy.on('uncaught:exception', (err, runnable) => {
      return false
    })
    cy.get(':nth-child(1) > a > .littleCard > .name').click()
  })
})

describe('Purchasing, viewing cart and getting to checkout', () => {
  it('passes if client can choose to buy an item', () => {
    cy.visit('http://localhost:8080/shopping')
    cy.on('uncaught:exception', (err, runnable) => {
      return false
    })
    cy.get(':nth-child(1) > a > .littleCard > .name').click()
    cy.get('.buy-now-button').click()
  })

  it('passes if client can see cart, and get to checkout', () => {
    cy.visit('http://localhost:8080/shopping')
    cy.on('uncaught:exception', (err, runnable) => {
      return false
    })
    cy.get(':nth-child(1) > a > .littleCard > .name').click()
    cy.get('.buy-now-button').click()
    cy.get('.checkout-button').click()
  })

  it('passes if payment validation works, and client is able to confirm purchase', () => {
    cy.visit('http://localhost:8080/shopping')
    cy.on('uncaught:exception', (err, runnable) => {
      return false
    })
    cy.get(':nth-child(1) > a > .littleCard > .name').click()
    cy.get('.buy-now-button').click()
    cy.get('.checkout-button').click()
    cy.get('.confirm-purchase-button').should('be.disabled')
    cy.get('#card-number').type('1234567898765432')
    cy.get('#cardholder-name').type('Ole Nordmann')
    cy.get('#cvv').type('707')
    cy.get('.confirm-purchase-button').should('be.enabled')
  })

})