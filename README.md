# CoderHack: 

## Introduction

Welcome to CoderHack, an advanced user management system developed with the modern Spring Boot framework. This application is designed to demonstrate sophisticated CRUD operations (Create, Read, Update, Delete) alongside the management of user attributes such as badges and scores. With full integration of MongoDB, CoderHack offers seamless data handling and persistence. The inclusion of Swagger enhances the development experience by providing an interactive API documentation interface.

## Features

- RESTful API for user management.
- Integration with MongoDB for data storage.
- Comprehensive API documentation with Swagger.
- Support for user attributes including badges and scores.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 11 or newer.
- Maven 3.2 or above.
- An instance of MongoDB running locally or remotely.

### Installation

1. Clone the repository:
   ```bash
   git clone [your-repository-link]
   cd CoderHack
   ```
2. Update the MongoDB URI in `src/main/resources/application.properties`:
   ```properties
   spring.data.mongodb.uri=mongodb://<username>:<password>@<host>:<port>/<database>
   ```
3. Build the application with Maven:
   ```bash
   ./mvnw clean install
   ```

### Running the Server

To launch the CoderHack application, execute:
```bash
./mvnw spring-boot:run
```
The server will start and be accessible at `http://localhost:8080`.

## API Documentation with Swagger

Navigate to `http://localhost:8080/swagger-ui.html` to access the Swagger UI, where you can explore and test the available RESTful endpoints provided by CoderHack.

## Detailed API Endpoints

### User Operations

#### Create User

- **POST** `/users`
- Body: `{ "userName": "username", "userId": "user@example.com" }`
- Success Response: 201 (Created), Returns the ID and details of the created user.

#### Update User

- **PUT** `/users/{id}`
- Body: `{ "score": 10 }`
- Success Response: 200 (OK), Returns updated user details.

#### Retrieve User Details

- **GET** `/users/{id}`
- Success Response: 200 (OK), Returns the details of the specified user.

#### Delete User

- **DELETE** `/users/{id}`
- Success Response: 200 (OK), Returns a confirmation of deletion.

#### List Users

- **GET** `/users`
- Success Response: 200 (OK), Returns a list of all users.

## Contributing

Your contributions are what make the open-source community such an amazing place to learn, inspire, and create. Any contributions you make to the CoderHack project are **greatly appreciated**.


## Acknowledgments

- The Spring Boot team for their outstanding framework.
- MongoDB for the flexible, scalable database solutions.
