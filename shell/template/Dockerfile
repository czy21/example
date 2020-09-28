FROM openjdk:11.0.4-jdk-slim
VOLUME ["/home/erp/resources","/home/erp/log"]
EXPOSE 8075
COPY ${{{param_api_archive_file_name}}} /home/erp/
RUN ls -al /home/erp/
ENTRYPOINT ["java","-jar","/home/erp/api.jar","--spring.config.location=/home/erp/resources/"]