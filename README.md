# MVC Music Store

Java version of the old [ASP.NET MVC Music Store](https://learn.microsoft.com/en-us/aspnet/mvc/overview/older-versions/mvc-music-store/) sample application for purely nostalgic reasons.

## Dependencies

- Java 21 JDK
- Docker
- Maven Wrapper, included in the repo as `mvnw` / `mvnw.cmd`

The app uses:

- Spring Boot
- Spring Web
- Thymeleaf
- Spring Data JPA
- PostgreSQL Driver
- Spring Security
- Spring Validation
- Docker Compose support

## Start the app

Make sure Docker is running.

```bash
./mvnw spring-boot:run
```

Navigate to [http://localhost:8080](http://localhost:8080) in your browser.

## Authentication

Default login:

`admin / password`

You can override it with environment variables:

`ADMIN_USERNAME`
`ADMIN_PASSWORD`
