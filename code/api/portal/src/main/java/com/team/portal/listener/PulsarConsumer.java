package com.team.portal.listener;

import com.czy.learning.pulsar.annotation.PulsarListener;
import com.czy.learning.pulsar.core.PulsarTemplate;
import org.apache.pulsar.client.api.PulsarClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.stereotype.Service;

@Service
public class PulsarConsumer {

    @Autowired
    PulsarTemplate pulsarTemplate;
    @Autowired
    PulsarClient pulsarClient;

    @PulsarListener(topic = "aa")
    public void a() {
        System.out.println("a");
    }

    @PulsarListener(topic = "bb")
    public void b() {
        System.out.println("b");
    }
}
