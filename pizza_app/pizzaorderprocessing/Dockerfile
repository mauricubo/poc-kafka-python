FROM openjdk:21-bookworm

WORKDIR /app

COPY ./target/pizzaorderprocessing-1.0-SNAPSHOT-jar-with-dependencies.jar ./app.jar

CMD ["java", "-jar", "app.jar"]