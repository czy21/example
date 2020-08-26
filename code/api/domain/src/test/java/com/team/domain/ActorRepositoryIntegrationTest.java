package com.team.domain;

import com.team.domain.node.Actor;
import com.team.domain.node.ActorRepository;
import com.team.domain.node.Movie;
import com.team.domain.node.Role;
import org.apache.commons.lang3.tuple.MutablePair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ActorRepositoryIntegrationTest {
    @SpringBootApplication
    static class ExampleConfig {
    }

    @Autowired
    ActorRepository actorRepository;

    @Test
    public void save() {

        Actor actor1 = getActor(
                "甄子丹",
                List.of(
                        MutablePair.of("导火索", "警官"),
                        MutablePair.of("锦衣卫", "青龙"),
                        MutablePair.of("武侠", "唐龙")
                ));

        Actor actor2 = getActor(
                "周润发",
                List.of(
                        MutablePair.of("纵横四海", "阿海"),
                        MutablePair.of("赌神", "高进"),
                        MutablePair.of("赌侠", "高进")
                ));


        actorRepository.saveAll(List.of(actor1, actor2));
    }

    private Actor getActor(String actorName, List<MutablePair<String, String>> movieRole) {
        Actor actor = new Actor(actorName);
        actor.setRoles(movieRole.stream().map(s -> new Role(actor, s.right, new Movie(s.left))).collect(Collectors.toList()));
        return actor;
    }

}
