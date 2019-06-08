package com.team.core.configure;


import com.team.core.annotations.Pocket;
import com.team.core.annotations.Pockets;
import com.team.provider.ResponseProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashMap;


@ControllerAdvice
public class ResponseConfigure implements ResponseBodyAdvice {


    @Autowired
    private ResponseProvider responseProvider;

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        HashMap<String, Object> res = new HashMap<>();
        if (request.getURI().getPath().startsWith("/api")) {
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
            responseProvider.read().entrySet().forEach(s -> {
                s.getValue().excute(res);
            });
            return res;
        }
        return body;
    }
}
