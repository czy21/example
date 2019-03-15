
docker run --name mysql-master -p 30579:3306 -v /d/Database/MySQL/Docker/Master/Data:/var/lib/mysql -v /d/Database/MySQL/Docker/Master/mysql.cnf:/etc/mysql/conf.d/mysql.cnf -v /d/Database/MySQL/Docker/Master/mysqld.cnf:/etc/mysql/mysql.conf.d/mysqld.cnf -e MYSQL_ROOT_PASSWORD=sasa -d mysql:5.7

pause