FROM openjdk:17-jdk-slim

WORKDIR /app

COPY build/libs/adaptors-batch-0.0.1-SNAPSHOT.jar adaptors-batch.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "adaptors-batch.jar"]

ENV TZ=Asia/Seoul
