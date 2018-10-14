package com.czy.core.mvc;


import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Arrays;
import java.util.HashMap;


@ControllerAdvice
public class ResponseConfig implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        HashMap<String, Object> res = new HashMap<>();
        if (returnType.hasMethodAnnotation(Pocket.class)) {
            Pocket pocket = returnType.getMethodAnnotation(Pocket.class);
            if (pocket != null) {
                res.put("pocket", PocketConfig.InjectData(Arrays.asList(pocket.entity())));
            }
        }
        res.put("data", body);
        return res;
    }
}
