 #!/bin/bash

set -e

sudo wget https://storage.googleapis.com/harbor-releases/release-1.7.0/harbor-offline-installer-v1.7.4.tgz -O - | tar -zxvf - -C /usr/local/lib/ 

