FROM openjdk:11.0.4-jdk-slim
VOLUME ["/home/erp/resources","/home/erp/log"]
EXPOSE ${{{param_injected['param_api_container_port']}}}
ARG JAR_FILE
ARG JAR_RESOURCES
COPY ${JAR_FILE} /home/${{{param_injected['param_project_name']}}}/
COPY ${JAR_RESOURCES} /home/${{{param_injected['param_project_name']}}}/
RUN ls -al /home/${{{param_injected['param_project_name']}}}/
ENTRYPOINT ["java","-jar","/home/${{{param_injected['param_project_name']}}}/api.jar","--spring.config.location=/home/${{{param_injected['param_project_name']}}}/resources/"]