package com.team.portal.controller;

//import org.springframework.session.Session;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.cooperated.controller.BaseController;
import com.team.security.annotation.Login;
import com.team.security.session.MapSession;
import com.team.security.session.SessionContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.Map;
import java.util.stream.IntStream;

@Slf4j
@RestController
@RequestMapping(path = "session")
public class SessionController extends BaseController {


    @Autowired
    StringRedisTemplate redisTemplate;
    @Autowired
    ObjectMapper objectMapper;

    @GetMapping(path = "test1")
    public Map<String, Object> test1(HttpServletRequest request) {
        var s = RequestContextHolder.getRequestAttributes().getSessionMutex();
        var session = request.getSession();
        return Map.of();
    }

    @Login
    @GetMapping(path = "initCount")
    public Map<String, Object> initCount(@RequestParam("count") String count) {
        IntStream.rangeClosed(1,Integer.parseInt(count)).mapToObj(Integer::toString)
                .forEach(t->{
            var session = new MapSession(t);
            try {
                redisTemplate.opsForValue().set(StringUtils.join(new String[]{"s", t}, ":"), objectMapper.writeValueAsString(session));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
        return Map.of();
    }

    @GetMapping(path = "test2")
    public Map<String, Object> test2(@RequestParam("sid") String sid) throws InterruptedException {
        String userId = SessionContext.getUserId();
        if (!userId.equals(sid)) {
            log.info(MessageFormat.format("userId: {0} sid: {1}", userId, sid));
        }
        return Map.of();
    }

    @Login
    @GetMapping(path = "login")
    public Map<String, Object> login(@RequestParam("sid") String sid) throws JsonProcessingException {
        var session = new MapSession(sid);
        redisTemplate.opsForValue().set(StringUtils.join(new String[]{"s", sid}, ":"), objectMapper.writeValueAsString(session));
        return Map.of();
    }

}
