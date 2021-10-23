package com.team.security.session;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.session.MapSession;
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
        throw new RuntimeException("请登录");
    }

    @Override
    public void save(Session session) {
        log.debug("session save: " + session.toString());
    }

    @Override
    public Session findById(String id) {
        var session = new MapSession(id);
        log.debug(StringUtils.join(new String[]{"session findById =>" + id, "value =>" + session.toString()}, " "));
        return session;
    }

    @Override
    public void deleteById(String id) {
        log.debug(StringUtils.join(new String[]{"session deleteById =>", id}, " "));
    }
}
