package com.team.application.storm.numcount;

import org.apache.storm.Config;

public class StormLocalConfig {
	
	public static Config config() {
		Config config = new Config();
        config.setDebug(false);
        config.setNumWorkers(1);
        return config;
	}

}
