ARG GRADLE_VERSION=7.6
FROM gradle:${GRADLE_VERSION} AS builder
WORKDIR /app
# Copy necessary directory
COPY build.gradle ./build.gradle
COPY settings.gradle ./settings.gradle
COPY src ./src
# COPY . . 
RUN gradle build -x test  
# -x test : means skip the test
# serve
FROM openjdk:17
ARG PORT=8080
ENV PORT=${PORT}
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar  app.jar
#VOLUME [ "/app/filestorage/images" ]
EXPOSE 8080
ENTRYPOINT ["java", "-jar","app.jar","--server.port=${PORT}"]
