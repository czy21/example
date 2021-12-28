#!/bin/bash

project_name={{ param_project_name }}_{{ param_env_suffix }}
docker exec -i {{ param_web_container_name }} rm -rf /root/${project_name}/
docker cp {{ param_web_output_path }} {{ param_web_container_name }}:/root/${project_name}/

docker cp {{ param_web_nginx_output_file_path }} {{ param_web_container_name }}:/etc/nginx/conf.d/

docker exec -i {{ param_web_container_name }} nginx -s reload