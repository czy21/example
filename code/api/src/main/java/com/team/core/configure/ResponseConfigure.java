package com.team.core.configure;


import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Predicate;
import com.team.core.mvc.Pocket;
import netscape.javascript.JSObject;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.UiConfiguration;

import java.util.Arrays;
import java.util.HashMap;


@ControllerAdvice
public class ResponseConfigure implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (request.getURI().getPath().startsWith("/api")) {
            HashMap<String, Object> res = new HashMap<>();
            if (returnType.hasMethodAnnotation(Pocket.class)) {
                Pocket pocket = returnType.getMethodAnnotation(Pocket.class);
                if (pocket != null) {
                    res.put("pocket", PocketConfigure.InjectData(Arrays.asList(pocket.entity())));
                }
            }
            res.put("data", body);
            return res;
        }
        return body;
    }
}
