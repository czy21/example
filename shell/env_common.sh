#!/bin/bash
shopt -s expand_aliases

if [ -f  "~/.bash_aliases" ];then
  source ~/.bash_aliases
fi

sh_file="${0}"
sh_name="$(basename ${0})"
run_py=${dir}/../../script/run.py