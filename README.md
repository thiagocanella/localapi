# LocalAPI

## Description

LocalAPI is a Spring Boot application designed for local usage of general APIs. It provides endpoints for logging and retrieving IoT data, supporting CRUD operations and flexible search capabilities. The project uses H2 in-memory database for persistence and exposes a RESTful interface for interaction.

## Features

- Store IoT data entries with name, registry, and timestamp
- Retrieve data by ID or list all entries
- Search data by parameters (name, registry, createdAt)
- Delete entries by ID
- OpenAPI documentation enabled via SpringDoc

## Technologies

- Java 21
- Spring Boot 3.3.2
- Spring Data JPA
- H2 Database
- Lombok
- JUnit & Mockito for testing

## Getting Started

1. Clone the repository.
2. Build and run the project using Maven:

   ```sh
   ./mvnw spring-boot:run
   ```

3. Access the API at [http://localhost:8027/localapi/iot-data-logger](http://localhost:8027/localapi/iot-data-logger).

## API Endpoints

- `POST /iot-data-logger` - Save new IoT data
- `GET /iot-data-logger/{id}` - Get data by ID
- `GET /iot-data-logger` - List all data
- `GET /iot-data-logger/search` - Search data by parameters
- `DELETE /iot-data-logger/{id}` - Delete data by ID

## License

This project is licensed under the Apache License 2.0.