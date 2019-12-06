From openjdk:11-jdk-alpine
VOLUME /home/erp
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} /home/erp/api.jar
ENTRYPOINT ["java","-jar","/home/erp/api.jar"]