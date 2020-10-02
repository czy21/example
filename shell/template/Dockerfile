FROM openjdk:11.0.4-jdk-slim
VOLUME ["/home/erp/resources","/home/erp/log"]
EXPOSE ${{{param_injected['param_api_container_port']}}}
ARG JAR_FILE
COPY ${JAR_FILE} /home/erp/
RUN ls -al /home/erp/
ENTRYPOINT ["java","-jar","/home/erp/api.jar","--spring.config.location=/home/erp/resources/"]