FROM openjdk:8-jdk-alpine

MAINTAINER jm5619

RUN mkdir /app

WORKDIR /app

ADD ./target/ir-property-rental-1.0.0-SNAPSHOT.jar /app

EXPOSE 8080

CMD ["java", "-jar", "ir-property-rental-1.0.0-SNAPSHOT.jar"]
