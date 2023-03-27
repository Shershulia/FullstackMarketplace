# Vue3 & SpringBoot Fullstack Application
## BuyBetter
Created by: Vladimir Lenkov, Ivan Shirshikov, Adrian Gunnar Lauterer

![image](https://user-images.githubusercontent.com/124590485/228015854-85cff626-023e-4a96-b143-74a33a629b2f.png)


Welcome to BuyBetter, an eCommerce application made with usability and design in mind. Enjoy a clean and efficient shopping experience at your fingertips for completely free.
This is a fullstack web application built with Vue3 on the frontend and SpringBoot on the backend. The application is similar to Finn.no, a popular online marketplace in Norway.
It was built over a work week as a project for the IDATT2105 fullstack course at NTNU.

As a warning, this was developed by a group of three under a short time period. A lot of the settings are currently not intended for development, undocumented features (bugs) will probably be present, and there might be some lack of features, loose security and faulty optimisation.

## Project Setup

### Frontend Setup

To setup the frontend, navigate to the `frontend` directory and run the following commands:

```
npm install
npm run serve
```

This will install all the necessary dependencies and start the frontend server.

### Backend Setup

To setup the backend, navigate to the `backend` directory and run the following commands:

```
mvn install
mvn spring-boot:run
```


This will install all the necessary dependencies and start the backend server.

## Usage

Once both the frontend and backend servers are running, you can access the application at `http://localhost:8080`. The application allows users to browse and search for various products that are available for sale.

Users can create an account and log in to save their favorite products, create listings for their own products, and contact sellers directly through the application.

## Functionality

Basic users and guests are able to use the following functionality:
- Browse catalogue
- Select items
- Add items to cart
- Buy items
- Confirm purchase at checkout
- Register new user
- Log in
- Change language
- Create items

Admins are specialized users who have access to the following:
- Add new categories
- Remove categories
- Edit categories
- Remove items

## Technologies Used

### Frontend Technologies

- Node.js
- Vue3
- Vue Router
- Vue I18n
- Vuex
- Axios
- ESlint
- Babel

### Backend Technologies

- SpringBoot
- Spring Data JPA
- Spring Security
- MySQL
- H2 Database
- Maven

## Security

BuyBetter features security on both backend and frontend in order to prevent sensitive information from being accessed by unauthorized users. Inside of the backend, JWT is used alongside Spring Security in order to encrypt the information sent between the server and the client. For every request which is sent to the backend, the token is validated before returning a response. In addition, Spring Security allows us to filter access to certain endpoints to prevent unauthorized access altogether. Role authentication is also performed by Spring Security to ensure that certain users have strictly specified permissions.

## Database Diagram
![Database](https://cdn.discordapp.com/attachments/354734844233777152/1089935966866780230/image.png)

## CI/CD

In order to ensure a smoother workflow, continuous integration and development was implemented on GitHub in order to prevent merging conflicts between branches when creating pull requests. Since GitHub has an integrated merge conflict checker, a customized workflow was not required because we could use the default feature. However, some CI/CD actions was implemented to make sure frontend can install all necessary dependencies and successfully build the frontend application. The workflow file can be found here: [CI/CD](https://github.com/Shershulia/FullstackMarketplace/actions)

## Testing

Testing was done in order to ensure that clients and backend operate properly and are able to communicate with eachother. The following technologies have been used for testing:

- Frontend: Cypress for E2E and component testing
- Backend: JUnit for JPA repository, model and Spring Boot testing

Running the tests: 

- For Cypress, ensure that backend and frontend are operational using the following commands:
```
mvn install
mvn spring-boot:run
npm install
npm run serve
```
- Run the tests by executing the following commands:
```
cd frontend
npx cypress run
```
- For experienced Cypress users, the following command may be used as well:
```
npx cypress open
```
- For backend testing, ensure Maven is installed and configured correctly, then run the following commands:
```
cd backend
mvn test
```
 
## Potential Future Work
Due to limited time constraints and work resources, some of the intended features may be lacking and should be worked upon in the future. The following is a list of what we wish we could have implemented or upgraded in order to provide a greater user experience:

### Integrated Chat Application
An integrated chat application where registered users could communicate and negotiate with one another would be a good idea to implement to avoid using other third party software or media in order to communicate. Integrated chat would remove the inconvenience of communicating using email and provide the users with an option to negotiate prices and discuss products in realtime.

### Host Website On A Dedicated Server
A convenient feature would be to use a dedicated service which would provide the users with a constantly active website instead of having to install and set up the project on their own computer. A potentially good service for this could be the one provided by Vercel. However, due to the limited time we have decided to ensure that the project can successfully run on any computer without requiring any major changes.

### Transaction History
Allowing the users to see their own transaction history would be a nice feature to have so that the user can better keep track of purchased and sold items, alongside with how much money they have spent or received from said transaction.

## License

This project is currently not under any license.

