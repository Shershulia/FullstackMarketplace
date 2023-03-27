describe('Home page client browsing', () => {
  it('passes if client can get to the site and see the home page', () => {
    cy.visit('http://localhost:8080/')
  })

  it('passes if client can use the navigation bar and change language', () => {
    cy.visit('http://localhost:8080/')
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
    cy.get('#to-about-us').click()
    cy.scrollTo('bottom')
  })
})

describe('Testing of signing up', () => {
  it('passes if client can get to the sign up page and register', () => {
    cy.visit('http://localhost:8080/register-user')
  })

})