#!/bin/bash

bash -c "set -e;echo ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQC3nTRJ/aVb67l1xMaN36jmIbabU7Hiv/xpZ8bwLVvNO3Bj7kUzYTp7DIbPcHQg4d6EsPC6j91E8zW6CrV2fo2Ai8tDO/rCq9Se/64F3+8oEIiI6E/OfUZfXD1mPbG7M/kcA3VeQP6wxNPhWBbKRisqgUc6VTKhl+hK6LwRTZgeShxSNcey+HZst52wJxjQkNG+7CAEY5bbmBzAlHCSl4Z0RftYTHR3q8LcEg7YLNZasUogX68kBgRrb+jw1pRMNo7o7RI9xliDAGX+E4C3vVZL0IsccKgr90222axsADoEjC9O+Q6uwKjahemOVaau+9sHIwkelcOcCzW5SuAwkezv 805899926@qq.com > /etc/dropbear/authorized_keys"

if [ ! -f "/etc/opkg/distfeeds.conf_bak" ];then
    mv /etc/opkg/distfeeds.conf /etc/opkg/distfeeds.conf_bak
fi

tee /etc/opkg/distfeeds.conf <<-'EOF'
src/gz openwrt_core http://mirrors.ustc.edu.cn/lede/releases/18.06.1/targets/ramips/mt7621/packages
src/gz openwrt_base http://mirrors.ustc.edu.cn/lede/releases/18.06.1/packages/mipsel_24kc/base
src/gz openwrt_luci http://mirrors.ustc.edu.cn/lede/releases/18.06.1/packages/mipsel_24kc/luci
src/gz openwrt_packages http://mirrors.ustc.edu.cn/lede/releases/18.06.1/packages/mipsel_24kc/packages
src/gz openwrt_routing http://mirrors.ustc.edu.cn/lede/releases/18.06.1/packages/mipsel_24kc/routing
EOF

rm -f /usr/bin/ssh /usr/bin/scp
opkg update
opkg install openssh-client polipo

reboot