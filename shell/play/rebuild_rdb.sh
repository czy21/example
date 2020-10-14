#!/bin/bash

cd $(cd "$(dirname "$0")"; pwd)
python3 ./rebuild_rdb.py $@

sh 'sh shell/play/build_api_image.sh --param param_api_image_tag='"${image_tag}"' --skip-rm-output'