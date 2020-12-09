package com.team.application.storm;

import com.team.application.storm.numcount.LocalStorm;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartStormCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        // TODO Auto-generated method stub
        LocalStorm.startStorm();
    }

}
