# Vue3 & SpringBoot Fullstack Application

This is a fullstack web application built with Vue3 on the frontend and SpringBoot on the backend. The application is similar to Finn.no, a popular online marketplace in Norway.
It was built over a work week as a project for the idatt2105 fullstack course at NTNU.

As a warning this was developed by a group of three under a short time period, a lot of the settings are currently not intended for development, undocumented features (bugs) will probably be present, and there might be some feature lack, loose sequrity and bad optimisation.

## Project Setup

### Frontend Setup

To setup the frontend, navigate to the `frontend` directory and run the following commands:

´´´
npm install
npm run serve

´´´

This will install all the necessary dependencies and start the frontend server.

### Backend Setup

To setup the backend, navigate to the `backend` directory and run the following commands:

´´´

mvn install
mvn spring-boot:run

´´´


This will install all the necessary dependencies and start the backend server.

## Usage

Once both the frontend and backend servers are running, you can access the application at `http://localhost:8080`. The application allows users to browse and search for various products that are available for sale.

Users can create an account and log in to save their favorite products, create listings for their own products, and contact sellers directly through the application.

## Technologies Used

### Frontend Technologies

- Vue3
- Vue Router
- Vuex
- Axios

### Backend Technologies

- SpringBoot
- Spring Data JPA
- Spring Security
- MySQL


## License

This project is currently not under any license.
