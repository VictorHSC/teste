FROM openjdk:17-oracle

WORKDIR /app

COPY . /app

RUN mv ./src/main/resources/application.properties ./src/main/resources/application.properties.maven &&\
    mv ./src/main/resources/application.properties.docker ./src/main/resources/application.properties &&\
    ./mvnw clean package -DskipTests

EXPOSE 8081

CMD ["java", "-jar", "target/backend.jar"]