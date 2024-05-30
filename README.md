#OVERVIEW
This is a user service for the afroseats application. This service is used to register users in the app.

#Prerequisites
- Java 17
- Maven 3.6+
- Docker (optional), for containerization


#Getting Started
- Clone the afroseats-eureka repository
- clone the afroseats-api-gateway repository
- clone the afroseats-user-service repository
- run the afroseats-eureka service
- run the afroseats-api-gateway service
- run the afroseats-user-service service

#Build
- mvn clean install
- mvn spring-boot:run

#Usage
- Register a user
- Make a post request to http://localhost:2020/api/v1/users/create
- example request body:
  {

  "password": "securePassword123",
  "email": "mark.peterson@example.com",
  "firstName": "Mark",
  "lastName": "Peterson",
  "phoneNumber": "123-456-7890",
  "dateOfBirth": "1990-01-01",
  "gender": "MALE"
  }