# Указываем базовый образ с OpenJDK
FROM openjdk:17-alpine AS base


WORKDIR /app
COPY  build/libs/MS_ClinicService-0.0.1-SNAPSHOT.jar ./app.jar
EXPOSE 8080:8080
# Определяем точку входа, чтобы запускать приложение
ENTRYPOINT ["java", "-jar", "app.jar"]

