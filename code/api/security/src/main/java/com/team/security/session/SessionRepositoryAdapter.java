package com.team.security.session;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;

@Slf4j
public class SessionRepositoryAdapter implements SessionRepository<Session> {

    StringRedisTemplate redisTemplate;
    ObjectMapper objectMapper;

    public SessionRepositoryAdapter(StringRedisTemplate redisTemplate,
                                    ObjectMapper objectMapper) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public Session createSession() {
        log.debug("session create");
        return null;
    }

    @SneakyThrows
    @Override
    public void save(Session session) {
        log.debug("session save: " + session.toString());
        redisTemplate.opsForValue().set(StringUtils.join(new String[]{"s", session.getId()}, ":"), objectMapper.writeValueAsString(session));
    }

    @SneakyThrows
    @Override
    public Session findById(String id) {
        var stringSession = redisTemplate.opsForValue().get(StringUtils.join(new String[]{"s", id}, ":"));
        MapSession mapSession = null;
        if (StringUtils.isNotEmpty(stringSession)) {
            mapSession = objectMapper.readValue(stringSession, new TypeReference<>() {
            });
        }
        log.debug(StringUtils.join(new String[]{"session findById =>" + id, "value =>" + mapSession}, " "));
        return mapSession;
    }

    @Override
    public void deleteById(String id) {
        log.debug(StringUtils.join(new String[]{"session deleteById =>", id}, " "));
    }
}
