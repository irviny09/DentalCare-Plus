FROM openjdk:21-jdk-slim
ARG JAR_FILE=target/dentalcare_plus-0.0.1.jar
COPY {JAR_FILE} app_dentalcare.jar
EXPOSE 8080
ENTRYPOINT [ "java" , "-jar" , "app_dentalcare.jar" ]