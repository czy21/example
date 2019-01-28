#!/bin/bash

set -e
# mysql
sudo yum -y install mysql-community-server
sudo bash -c 'echo -e "
[client]
default-character-set=utf8
[mysql]
default-character-set=utf8
[mysqld]
character-set-server=utf8
default-storage-engine=INNODB
lower_case_table_names=1
datadir=/var/lib/mysql
socket=/var/lib/mysql/mysql.sock
log-output=FILE
general-log=1
general_log_file=\"general.log\"
slow-query-log=1
slow_query_log_file=\"slow.log\"
long_query_time=10
log-error=\"error.err\"
symbolic-links=0
sql_mode=STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION
[mysqld_safe]
default-character-set=utf8
log-error=/var/log/mysqld.log
pid-file=/var/run/mysqld/mysqld.pid
" > /etc/my.cnf'
sudo service mysqld start
sudo service mysqld status