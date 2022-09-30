package com.team.stream.listener;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.messaging.Message;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Consumer;

@Slf4j
@Configuration
public class StreamListenerConfig {

    @Bean
    public Consumer<Message<String>> rabbitInput11() {
        return t -> {
            Long deliveryTag = t.getHeaders().get(AmqpHeaders.DELIVERY_TAG, Long.class);
            Channel channel = t.getHeaders().get(AmqpHeaders.CHANNEL, Channel.class);
            log.info("rabbit {} {}", t.getPayload(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
            try {
                channel.basicAck(deliveryTag, false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }

    @Bean
    public Consumer<List<String>> rabbitInput12() {
        return list -> {
            list.forEach(System.out::println);
        };
    }

    @Bean
    public Consumer<String> kafkaInput21() {
        return t -> {
            log.info("kafka {} {}", t, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
        };
    }

    @Bean
    public Consumer<List<String>> kafkaInput22(JdbcTemplate jdbcTemplate, @Value("${kafka-to-mysql.batch-size}") Integer batchSize) {
        return t -> {
            ListUtils.partition(t, batchSize).forEach(p -> batchInsert(jdbcTemplate, p));
            log.info("kafka {} {} {}", t.size(), t, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
        };
    }

    private static void batchInsert(JdbcTemplate jdbcTemplate, List<String> p) {
        jdbcTemplate.batchUpdate("insert kafka_topic(value) values (?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, p.get(i));
            }

            @Override
            public int getBatchSize() {
                return p.size();
            }
        });
    }

}
