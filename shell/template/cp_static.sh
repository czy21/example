#!/bin/bash

project_name=${{{param_injected['param_project_name']}}}_${{{param_injected['param_env_suffix']}}}
docker exec -i ${{{param_injected['param_web_container_name']}}} rm -rf /root/${project_name}/
docker cp ${{{param_web_output_path}}} ${{{param_injected['param_web_container_name']}}}:/root/${project_name}/