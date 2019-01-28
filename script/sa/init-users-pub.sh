#!/bin/bash

for file in $(ls ./pubs/*.pub);
    do 
    pub_content="command=\"read\",no-X11-forwarding,no-agent-forwarding,no-pty,no-user-rc ""$(cat $file)"
    rsa_pubs= echo $rsa_pubs$pub_content
done
./user-tunnel.sh cent_a -create < $rsa_pubs