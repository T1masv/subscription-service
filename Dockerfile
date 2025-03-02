FROM openjdk:21-jdk-bullseye
RUN addgroup --system spring && \
    adduser --system spring && \ 
    adduser spring spring 
USER spring:spring
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
