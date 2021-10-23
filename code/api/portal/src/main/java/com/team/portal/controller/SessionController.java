package com.team.portal.controller;

//import org.springframework.session.Session;
import com.team.cooperated.exception.BusinessErrorCode;
import com.team.cooperated.exception.BusinessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping(path = "session")
public class SessionController {


    @GetMapping(path = "test1")
    public Map<String, Object> test1(HttpServletRequest request) {
        var s = RequestContextHolder.getRequestAttributes().getSessionMutex();
        var session = request.getSession();
        return Map.of();
    }

    @GetMapping(path = "test2")
    public Map<String, Object> test2(HttpServletRequest request) {
        throw new BusinessException(BusinessErrorCode.NO_EXIST_USER);
//        return Map.of();
    }
}
