#!/usr/bin/env python3

import sys

sys.path.append("../../")
from shell.local import _env
from script.domain.source import java as java_source

_env.env_common.default_common.param_api_docker_gradle_command = "sudo docker run --rm -u gradle -v \"$HOME\":\"$HOME\" -v \"$HOME\"/.gradle/init.gradle:/home/gradle/.gradle/init.gradle gradle:jdk11"
java_source.build_api()
