# Quotes Spring Boot API

This is a RESTful API built with Spring Boot that manages quotes. The application supports CRUD operations for quotes, including the ability to search quotes by text and retrieve random quotes.

## API Operations

1. **GET /api/quotes**: Retrieve all quotes or quotes by specific text (optional query parameter `text`).
2. **GET /api/quotes/{id}**: Retrieve a quote by its ID.
3. **GET /api/quotes/random**: Retrieve a random quote.
4. **POST /api/quotes**: Create a new quote.
    - Request body: `{ "author": "Author Name", "text": "Quote text" }`
5. **PUT /api/quotes**: Update an existing quote.
    - Request body: `{ "id": 1, "author": "Updated Author", "text": "Updated text" }`
6. **DELETE /api/quotes/{id}**: Delete a quote by its ID.

## How to Run the Application

### Run with Docker

To run the application with Docker and Docker Compose, use the following commands:

#### 1. Build and start the application:

```bash
docker-compose up --build
```
The Spring Boot app will be available at http://localhost:8080, and MySQL will run on port 3306.

The MySQL database is initialized with a predefined schema and sample data using the SQL scripts located in the db-scripts folder. These scripts are automatically executed when the MySQL container starts, creating the quotes table and inserting some initial quotes.


#### 2. Stop the application

```bash
docker-compose down
````
or if you also want to reset the database
```bash
docker-compose down --volumes
```