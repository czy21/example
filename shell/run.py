#!/usr/bin/env python3
import argparse
import importlib
import json
import sys
import os
from pathlib import Path

from script.utility import log as log_util, basic as basic_util

logger = log_util.Logger(__name__)


def exec_file(source_dict: {}):
    parser = argparse.ArgumentParser()
    parser.add_argument('--param', nargs="+", default=[])
    parser.add_argument('--skip-rm-output', action="store_false")
    args = parser.parse_args()
    default_path_module = importlib.import_module("script.domain.default.path")
    if args.skip_rm_output:
        getattr(default_path_module, "re_mkdir")(rm_output=True)

    # running action file
    action_env = Path(sys.argv[0])
    # empty source log
    open("".join([action_env.as_posix(), ".log"]), 'w').close()
    # injected param to global
    env_module = importlib.import_module("".join(["shell.", action_env.cwd().stem, "._env"]))
    getattr(getattr(env_module, "env_common"), "inject")(args)
    source_mod_files = [{"module": importlib.import_module(m), "func": f} for m, f in source_dict.items()]
    default_common_mod = importlib.import_module("script.domain.default.common")
    common_param = getattr(default_common_mod, "get_params")()
    logger.info(basic_util.action_formatter("params", json.dumps(common_param, sort_keys=True)))
    [getattr(i["module"], j)() for i in source_mod_files for j in i["func"]]
