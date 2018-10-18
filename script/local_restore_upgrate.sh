#!/bin/bash

DB_User="root"
DB_Pwd="sasa"
DB_Host="localhost"
DB_Name="czy_oa"

dir=$(cd "$(dirname "$0")";pwd)

mysql -u$DB_User -p$DB_Pwd $DB_Name \
< $dir/czy_oa.sql