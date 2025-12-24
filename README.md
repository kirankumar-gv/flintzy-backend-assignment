# Flintzy Social Media Integration Backend

## Overview:-

This project is a Spring Boot backend application that allows users to:

Authenticate using Google Login

Link their Facebook Pages

Publish posts to linked Facebook Pages

Secure APIs using JWT-based authentication

Handle errors gracefully with global exception handling

The application is designed with clean architecture principles, proper separation of concerns, and progress-based Git commits.

## Tech Stack:-

Java 18

Spring Boot 3.5.x

Spring Security + JWT

Spring Data JPA

MySQL

Facebook Graph API

Maven

Postman (for testing)

## Project Structure:-

com.flintzy.socialmedia
│
├── auth
│   ├── controller
│   ├── dto
│   ├── model
│   ├── service
│   └── serviceImpl
│
├── security
│   ├── JwtFilter
│   ├── JwtUtil
│   ├── CustomUserDetails
│   └── SecurityConfig
│
├── facebook
│   ├── controller
│   ├── entity
│   ├── repository
│   └── service
│   └── serviceImpl
│
├── post
│   ├── controller
│   ├── entity
│   ├── repository
│   └── service
│   └── serviceImpl
│
├── user
│   ├── entity
│   └── repository
│
└── common
    └── exception
    └── response


## Authentication Flow:-

User logs in using Google (gmail)

Backend generates a JWT token

JWT is sent in Authorization header for secured APIs

User details are stored in SecurityContext

## API Flow:-

Google Login
POST    /auth/google

Request Body GoogleLinkRequest

{
  "email":"YOUR_EMAIL",
 "name":"YOUR_NAME"
}

Response

{
  "token": "<JWT_TOKEN>"
}

Link Facebook Pages
POST   /facebook/link
Authorization: Bearer <JWT_TOKEN>


Request Body

{
  "userAccessToken": "<FACEBOOK_USER_ACCESS_TOKEN>"
}


Response

[
  {
    "pageId": "852957511245030",
    "pageName": "Flintzy Test Page"
  }
]

Publish Post to Facebook Page
POST /posts/publish
Authorization: Bearer <JWT_TOKEN>


Request Body

{
  "pageId": "852957511245030",
  "message": "Hello from Flintzy Social Media App"
}


Response

{
  "status": "SUCCESS",
  "facebookPostId": "852957511245030_122093526453190628"
}

## Error Handling:-

Global exception handling using @ControllerAdvice

Custom exceptions:

UnauthorizedException

ResourceNotFoundException

FacebookApiException

FacebookPageNotLinkedException

Clean, consistent error responses

## Database Setup:-

Create Database
CREATE DATABASE flintzy_db;

A MySQL database dump with schema and sample data is provided.

Location
/db/flintzy_db.sql

To import:

mysql -u root -p flintzy_db < db/flintzy_db.sql

## Configuration:-

Update application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/flintzy_db
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD

jwt.secret=YOUR_SECRET_KEY
jwt.expiration=3600000

facebook.graph.api.base-url=https://graph.facebook.com

## Running the Application

git clone <repository-url>
cd FlintzySocialmedia
mvn clean install
mvn spring-boot:run


Application will start on:

http://localhost:8080

## Testing

Use Postman

Follow API flow:

/auth/google

/facebook/link

/posts/publish

## Notes

Progress-based Git commits are used to show development stages

Services follow interface-based abstraction

Duplicate Facebook page entries are prevented

SecurityContext is used instead of repeated DB calls


## Author

Kiran Kumar G V
Java Backend Developer