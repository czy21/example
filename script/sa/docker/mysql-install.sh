#!/bin/bash

set -e

sudo mkdir /etc/mysql/

sudo bash -c 'echo -e "
[client]
default-character-set=utf8
[mysql]
default-character-set=utf8" > /etc/mysql/mysql.cnf'

sudo bash -c 'echo -e "
[mysqld]
character-set-server=utf8
default-storage-engine=INNODB
lower_case_table_names=1
pid-file  = /var/run/mysqld/mysqld.pid
socket    = /var/run/mysqld/mysqld.sock
datadir   = /var/lib/mysql
log-error = /var/log/mysql/error.log
sql_mode=STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION
log-output=FILE
general-log=1
general_log_file="general.log"
slow-query-log=1
slow_query_log_file="slow.log"
long_query_time=10
symbolic-links=0
" > /etc/mysql/mysqld.cnf'

sudo mkdir /var/lib/mysql

sudo docker run --name mysql-master -p 3372:3306 -v /var/lib/mysql:/var/lib/mysql -v /etc/mysql/mysql.cnf:/etc/mysql/conf.d/mysql.cnf -v /etc/mysql/mysqld.cnf:/etc/mysql/mysql.conf.d/mysqld.cnf -e MYSQL_ROOT_PASSWORD=Czy.101048 -d mysql:5.7