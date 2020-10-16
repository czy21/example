#!/usr/bin/env python3
import argparse
import importlib
import json
import sys
from pathlib import Path

sys.path.append(Path(__file__).joinpath("../../").resolve().as_posix())

from script.utility import log as log_util
from script.utility import basic as basic_util

logger = log_util.Logger(__name__)


def exec_file():
    parser = argparse.ArgumentParser()
    parser.add_argument('--param', nargs="+", default=[])
    parser.add_argument('--skip-rm-output', action="store_false")
    parser.add_argument('--env', required=True)
    parser.add_argument('--log-file')
    parser.add_argument('--cmd')
    args = parser.parse_args()

    env_path = Path(args.env).resolve()
    env_stem = env_path.parent.stem

    log_file = log_util.parse_argv(sys.argv, "--log-file")
    if log_file != "":
        open(env_path.joinpath("../", log_file).absolute().resolve().as_posix(), 'w').close()

    # empty source log
    default_path_module = importlib.import_module("script.domain.default.path")
    getattr(default_path_module, "re_mkdir")(rm_output=args.skip_rm_output)

    # injected param to global
    env_module = importlib.import_module("".join(["shell.", env_stem, "._env"]))
    getattr(getattr(env_module, "env_common"), "inject")(args)
    default_common_mod = importlib.import_module("script.domain.default.common")
    common_param = getattr(default_common_mod, "get_params")()
    logger.info(basic_util.action_formatter("params", json.dumps(common_param, sort_keys=True)))
    if args.cmd:
        exec(args.cmd)


if __name__ == '__main__':
    exec_file()
