FROM openjdk:16-jdk-alpine
VOLUME ["/home/erp/resources","/home/erp/log"]
EXPOSE ${{{param_api_container_port}}}
COPY ./api.jar /home/${{{param_project_name}}}/
COPY ./resources/ /home/${{{param_project_name}}}/resources/
RUN ls -al /home/${{{param_project_name}}}/
ENTRYPOINT ["java","-jar","/home/${{{param_project_name}}}/api.jar","--spring.config.location=/home/${{{param_project_name}}}/resources/"]