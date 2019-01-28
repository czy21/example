#!/bin/bash


for file in $(ls ./pubs/*.pub);
    do 
    rsa_pub="$rsa_pub"command=\"read\",no-X11-forwarding,no-agent-forwarding,no-pty,no-user-rc"`cat $file`\n"
done
./user-tunnel.sh cent_a -create < echo -e $rsa_pub