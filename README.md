# ForumHub

Challenge: Development of a backend model for a web forum.

Configure the JDBC URL, user, and password of your database in the file:

    /one--java-forum-hub/src/main/resources/application.properties

It is highly recommended that you use the environment variables already defined in the file to prevent failure in the execution of the database access.

Example:

    DB_HOST=localhost
    DB_USER=root
    DB_PASSWORD=123456
    DB_NAME=your_database

It is not recommended to use access values directly in the application.properties file unless the scraper is hosted on your localhost. You should return to the root directory:

    /one--java-forum-hub

And then use this Maven command:

    ./mvnw clean compile

And finally run the project with this command:

    ./mvnw spring-boot:run -q

For API documentation:

    http://localhost:8080/swagger-ui/index.html#/

This project was built with Java, Spring, Auth0 JWT and MySQL.