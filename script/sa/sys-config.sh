#!/bin/bash

set -e


sed -i -r "s/^\s*PermitRootLogin\s+yes/PermitRootLogin no/; s/^\s*PasswordAuthentication\s+yes/PasswordAuthentication no/; " /etc/ssh/sshd_config
service sshd restart
