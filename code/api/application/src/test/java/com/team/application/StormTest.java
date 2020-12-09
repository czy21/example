package com.team.application;

import com.team.application.storm.numcount.LocalStorm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class StormTest {
    @Test
    public void test1() throws Exception {
        LocalStorm.startStorm();
    }
}
