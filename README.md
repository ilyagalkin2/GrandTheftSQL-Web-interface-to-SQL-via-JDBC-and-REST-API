### GrandTheftSQL. Web interface to SQL via JDBC to PostgreSQL as Docker image, with REST API.

PostgreSQL runs in Docker on port 8088
Why?
Conflicts with port 5432 of actual installation of PostgreSQL

Command:

docker run --name postgres-city -p 8088:5432 -e POSTGRES_PASSWORD=somepass -e POSTGRES_USER=someuser -e POSTGRES_DB=city -d postgres:alpine

URL for JDBC:

spring.datasource.url = jdbc:postgresql://localhost:8088/city

