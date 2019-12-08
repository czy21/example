From openjdk:11.0.4-jdk-slim
VOLUME /home/erp
ARG apiPath
COPY ${apiPath} /home/erp/
ENTRYPOINT ["java","-jar","/home/erp/api.jar","--spring.config.location=/home/erp/resources"]