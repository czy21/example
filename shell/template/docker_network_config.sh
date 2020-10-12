
sudo docker network create --driver bridge erp_play_default
sudo docker network connect erp_play_default mysql
sudo docker network connect erp_play_default mongo
sudo docker network connect erp_play_default neo4j