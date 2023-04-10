package com.team.application.pool;


import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CounterImpl implements Counter {

    private AtomicInteger ai;
    private String id;
    private void init() {
        Logger.getLogger(CounterImpl.class.getName()).log(Level.INFO, "New bean instance created");
        ai = new AtomicInteger(0);
        SecureRandom random = new SecureRandom();
        id = new BigInteger(130, random).toString(32);
    }

    @Override
    public int count() {
        Logger.getLogger(CounterImpl.class.getName()).log(Level.INFO, "Bean id = {0}", id);
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException ex) {
            Logger.getLogger(CounterImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ai.incrementAndGet();
    }
}
