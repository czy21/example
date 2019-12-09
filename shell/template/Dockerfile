FROM openjdk:11.0.4-jdk-slim
VOLUME ["/home/erp/resources","/home/erp/log"]
EXPOSE 8075
ARG apiPath
COPY ${apiPath} /home/erp/
RUN ls -al /home/erp/
ENTRYPOINT ["java","-jar","/home/erp/api.jar","--spring.config.location=/home/erp/resources/"]