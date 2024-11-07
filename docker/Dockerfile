# Указываем базовый образ с OpenJDK
FROM alpine:latest AS base

RUN apk add git openjdk17
RUN git clone https://github.com/AngelIgor777/MS_ClinicService.git /project

WORKDIR /project
RUN ./gradlew bootJar
# Удаляем исходный код и .git для освобождения памяти
RUN rm -rf /project/.git /project/src /project/build/tmp /project/build/classes

FROM alpine:latest AS  build

RUN apk add openjdk17

WORKDIR /app
COPY --from=base /project/build/libs/*.jar ./app.jar
EXPOSE ${PORT:-8080}
ENTRYPOINT ["java","-jar","/app/app.jar"]

