package com.team.core.configure;


import com.team.core.annotations.Pocket;
import com.team.core.annotations.Pockets;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

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
            if (returnType.hasMethodAnnotation(Pockets.class) || returnType.hasMethodAnnotation(Pocket.class)) {
                Pockets pockets = returnType.getMethodAnnotation(Pockets.class);
                Pocket pocket = returnType.getMethodAnnotation(Pocket.class);
                if (pockets != null) {
                    res.put("pocket", PocketConfigure.InjectDatas(pockets));
                }
                if (pocket != null) {
                    res.put("pocket", PocketConfigure.InjectData(pocket));
                }
            }
            res.put("data", body);
            return res;
        }
        return body;
    }
}
