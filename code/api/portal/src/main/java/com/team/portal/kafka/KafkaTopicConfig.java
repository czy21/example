package com.team.portal.kafka;


import com.team.domain.entity.FooEntity;
import com.team.domain.mapper.FooMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

@Slf4j
@Configuration
public class KafkaTopicConfig {


    @Autowired
    private FooMapper fooMapper;

    @Bean
    public RecordMessageConverter converter() {
        return new StringJsonMessageConverter();
    }

    @KafkaListener(id = "fooGroup", topics = "topic1")
    public void listen(Foo1 foo) {
        FooEntity entity = new FooEntity();
        entity.setNumber(foo.getFoo());
        fooMapper.insert(entity);
    }

    @Bean
    public NewTopic topic() {
        return new NewTopic("topic1", 1, (short) 1);
    }
}
