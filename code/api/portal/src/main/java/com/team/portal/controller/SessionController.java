package com.team.portal.controller;

//import org.springframework.session.Session;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.cooperated.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping(path = "session")
public class SessionController extends BaseController {


    @GetMapping(path = "test1")
    public Map<String, Object> test1(HttpServletRequest request) {
        var s = RequestContextHolder.getRequestAttributes().getSessionMutex();
        var session = request.getSession();
        return Map.of();
    }

    @GetMapping(path = "test2")
    public Map<String, Object> test2(HttpServletRequest request) {
//        throw new BusinessException(BusinessErrorCode.NO_EXIST_USER);
//        return Map.of();
        System.out.println(request.getSession().getId());
        return Map.of();
    }

}
