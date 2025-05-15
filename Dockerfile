
# Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
# Click nbfs://nbhost/SystemFileSystem/Templates/Other/Dockerfile to edit this template

#Version con jdk17

FROM maven:3.8.5-openjdk-17 as build

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim

COPY --from=build /target/tienda-1.jar tienda.jar

EXPOSE 80

ENTRYPOINT ["java","-jar","tienda.jar"]
 