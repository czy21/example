FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD erp-operation-1.0.7.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]