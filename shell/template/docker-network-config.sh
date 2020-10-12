#!/bin/bash

set -e

project_network=${{{param_injected['param_project_name']}}}_${{{param_injected['param_env_suffix']}}}_default
sudo docker network create --driver bridge ${project_network}
sudo docker network connect ${project_network} ${{{param_main_db_mysql_host}}}
sudo docker network connect ${project_network} ${{{param_main_db_mongo_host}}}
sudo docker network connect ${project_network} ${{{param_main_db_neo4j_host}}}