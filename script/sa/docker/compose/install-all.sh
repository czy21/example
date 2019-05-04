#!/usr/bin/env bash


case "$2" in
        -upload)
        scp -r ././../compose/ $1:
        ssh $1 ' ./compose/install-all.sh cent_a -exec;'
        ;;
        -exec)
        cd compose
        for tar_dir in `ls .`
        do
          if [ -d ${tar_dir} ]
          then
          sudo ./${tar_dir}/config.sh
          sudo docker-compose -f ${tar_dir}/docker-compose.yml up -d
          fi
        done
        ;;
        *)
        echo -e "\033[40;33m unknow input param \033[0m"
        ;;
esac