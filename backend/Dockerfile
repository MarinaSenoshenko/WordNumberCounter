FROM openjdk:17-jdk-slim AS build
WORKDIR /
COPY . /
ENTRYPOINT ["java","-jar","build/libs/backend.jar"]