# Flintzy Social Media Integration Backend

## Overview:-

This project is a Spring Boot backend application that allows users to:

 - Login using Google OAuth
 - Connect their Facebook account
 - Fetch & link Facebook Pages
 - Publish posts to linked Facebook Pages
 - Secure APIs using JWT
 - Handle errors with centralized exception handling

The design follows clean architecture, layered separation.

## Tech Stack:-

- Java 18
- Spring Boot 3.5.x
- Spring Security + JWT
- Spring Data JPA
- MySQL
- Facebook Graph API
- Maven
- Postman & Browser (for testing)

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
│
├── facebook
│   ├── controller
│   ├── dto
│   ├── entity
│   ├── exception
│   ├── repository
│   ├── service
│   └── serviceImpl
│
├── post
│   ├── controller
│   ├── dto
│   ├── entity
│   ├── repository
│   ├── service
│   └── serviceImpl
│
├── user
│   ├── entity
│   └── repository
│
└── common
    ├── exception
    └── response


## Authentication Flow (OAuth + JWT):-

1) Google Login (Browser Redirect)
 
User opens:
http://localhost:8080/auth/google/login

Flow:

 - Redirects to Google OAuth
 - Google returns code
 - Backend exchanges code → email
 - User is created (if not exists)
 - JWT is generated and returned
 - Response:
   {
      "token": "<JWT_TOKEN>",
      "message": "User Login Successful"
   }

2) Connect Facebook (Browser Redirect)

User opens:
http://localhost:8080/auth/facebook/login

Flow:

 - Redirects to Facebook OAuth
 - Facebook returns code
 - Backend exchanges code → user access token
 - Token is stored against user in DB
 - Pages can now be accessed securely
 - Response:
   {
     "message": "Facebook connected successfully"
   }

## API Flow:-

Link Facebook Pages

POST   /facebook/link
Authorization: Bearer <JWT_TOKEN>

Response
[
  {
    "pageId": "PAGE_ID",
    "pageName": "PAGE_NAME"
  }
]

Publish Post to Facebook Page

POST /posts/publish
Authorization: Bearer <JWT_TOKEN>


Request Body
{
  "pageId": "PAGE_ID",
  "message": "Hello from Flintzy Social Media App"
}


Response
{
  "status": "SUCCESS",
  "facebookPostId": "POST_ID"
}

## Error Handling:-

  Global exception handling using @ControllerAdvice

Custom exceptions:

 - UnauthorizedException
 - ResourceNotFoundException
 - FacebookApiException
 - FacebookPageNotLinkedException
 - UserNotLoggedInException

Clean, consistent error responses

Example response:-
{
  "message": "Invalid or expired token",
  "status": 401,
  "timestamp": "2026-01-02T14:33:00"
}

## Login Sequence, Cookie Behavior & Security Rules

This application enforces a strict and secure login sequence:

 -  User must first sign in using Google OAuth
 -  After successful login, the backend generates a JWT token
 -  The JWT is stored in the browser as an HTTP-only cookie
 -  When the user clicks Connect Facebook, the backend retrieves the JWT from the cookie and identifies the user
 -  Facebook OAuth completes, and the Facebook access token is securely stored for that user

## Cookie Expiry (Security Behavior)

 - The cookie is intentionally configured with a 2-minute expiry (temporary)
 - If the user tries Facebook login after 2 minutes, the cookie expires
 - The backend no longer recognizes the user and immediately throws: UserNotLoggedInException
 - This ensures Facebook accounts are never linked unless the user is recently authenticated
 - This behavior demonstrates session-safety and avoids accidental or unauthorized account linking

## Database Setup:-

Create Database
CREATE DATABASE flintzy_db;

A MySQL database dump with schema and sample data is provided.

Location
/db/flintzy_db.sql

To import:

mysql -u root -p flintzy_db < db/flintzy_db.sql

## Configuration:-

## Update application.properties:
- spring.datasource.url=jdbc:mysql://localhost:3306/flintzy_db
- spring.datasource.username=root
- spring.datasource.password=YOUR_PASSWORD

## JWT Configuration
- jwt.secret=YOUR_SECRET_KEY
- jwt.expiration=3600000

## Facebook Graph API
- facebook.graph.api.base-url=https://graph.facebook.com

## Facebook OAuth Settings
- facebook.app.id=<FACEBOOK_DEVELOPER_APP_ID>
- facebook.app.secret=<FACEBOOK_DEVELOPER_APP_SECRET>
- facebook.redirect.uri=http://localhost:8080/auth/facebook/callback
- facebook.api.version=v24.0   
- facebook.oauth.url=https://www.facebook.com/v24.0/dialog/oauth
- facebook.token.url=https://graph.facebook.com/v24.0/oauth/access_token

# Google OAuth Settings
- google.client.id=<GOOGLE_CLIENT_ID>
- google.client.secret=<GOOGLE_CLIENT_SECRET>
- google.redirect.uri=http://localhost:8080/auth/google/callback
- google.oauth.url=https://accounts.google.com/o/oauth2/v2/auth
- google.token.url=https://oauth2.googleapis.com/token
- google.userinfo.url=https://www.googleapis.com/oauth2/v3/userinfo

## Running the Application

git clone <repository-url>
cd FlintzySocialmedia
mvn clean install
mvn spring-boot:run


Application will start on:

http://localhost:8080

## Testing Guide (Step-by-Step)

Follow this sequence exactly.

1) Login with Google (Browser)

  - Open in browser: http://localhost:8080/auth/google/login
  - Google login page appears
  - After successful login:
  - User is created in DB (if new)
  - A JWT cookie is stored
  - Backend returns a success response
  - Response Example 
    {
      "token": "<JWT_TOKEN>",
      "message": "User Login Successful"
    }

2) Connect Facebook (Browser)

  - Open in browser: http://localhost:8080/auth/facebook/login
  - Redirects to Facebook OAuth
  - After user grants permission:
  - Backend exchanges code → Facebook access token
  - Token is stored in DB mapped to the authenticated user
  - Response example:
    {
      "message": "Facebook connected successfully"
    }

3) Link Facebook Pages (Postman)

  - POST request: POST http://localhost:8080/facebook/link
  - Headers: Authorization: Bearer <JWT_TOKEN>
  - The backend extracts the user’s email from the JWT, retrieves the stored Facebook access token for that user, and uses it to fetch and link Facebook pages
  - Response:
    [
      {
        "pageId": "PAGE_ID",
        "pageName": "PAGE_NAME"
      }
   ]

4) Publish a Post (Postman)

  - POST request: POST http://localhost:8080/posts/publish
  - Headers: Authorization: Bearer <JWT_TOKEN>
  - Body:
    {
      "pageId": "PAGE_ID",
      "message": "Hello from Flintzy Social Media App"
    }
  - Response:
    {
      "status": "SUCCESS",
      "facebookPostId": "POST_ID"
    }

## Notes

 - Login must always start with Google OAuth
 - Facebook login works only if user is authenticated
 - Cookie expiry (2 minutes) enforces secure flow
 - The JWT cookie is HTTP-only, meaning it cannot be accessed via browser JavaScript and is safe from XSS attacks.
 - Facebook pages and posts use stored Facebook tokens
 - JWT protects all private APIs

## Author

Kiran Kumar G V 
Java Backend Developer