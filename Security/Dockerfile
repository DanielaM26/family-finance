FROM openjdk:17
VOLUME /tmp
EXPOSE 8070
ARG JAR_FILE=build/libs/security-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]