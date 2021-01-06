package com.team.portal.kafka;


import com.team.domain.entity.FooEntity;
import com.team.domain.mapper.FooMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Configuration
public class KafkaTopicConfig {


    @Autowired
    private FooMapper fooMapper;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Bean
    public RecordMessageConverter converter() {
        return new StringJsonMessageConverter();
    }

    @KafkaListener(id = "fooGroup", topics = "topic1")
    public void listen(Foo1 foo) {
//        FooEntity entity = new FooEntity();
//        entity.setNumber(foo.getFoo());
//        fooMapper.insert(entity);
//        jdbcTemplate.update("insert into foo (id,number)values(?,?)",
//                ps -> {
//                    ps.setString(1, UUID.randomUUID().toString());
//                    ps.setString(2, entity.getNumber());
//                }
//        );
        log.info(StringUtils.join("foo-",foo.getFoo()));
    }

    @Bean
    public NewTopic topic() {
        return new NewTopic("topic1", 1, (short) 1);
    }

    @Bean
    public NewTopic streamWordCountInput() {
        return new NewTopic("stream-wordCount-input", 1, (short) 1);
    }

    @Bean
    public NewTopic streamWordCountOutput() {
        return new NewTopic("stream-wordCount-output", 1, (short) 1);
    }


}
