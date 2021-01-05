package com.team.cooperated.advice;

import com.team.cooperated.annotation.Description;
import com.team.cooperated.annotation.EnumPocket;
import com.team.cooperated.annotation.PocketName;
import com.team.cooperated.annotation.SpecialPocket;
import com.team.cooperated.controller.BaseController;
import com.team.cooperated.model.simple.SimpleItemModel;
import com.team.cooperated.pocket.PocketProvider;
import org.apache.commons.lang3.tuple.MutablePair;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.smile.MappingJackson2SmileHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@ControllerAdvice(assignableTypes = BaseController.class)
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

    private static Map<String, PocketProvider<?>> POCKET_CACHE = new ConcurrentHashMap<>();

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return converterType.isAssignableFrom(MappingJackson2HttpMessageConverter.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        Map<String, Object> result = new HashMap<>();
        result.put(BaseController.RESPONSE_TIMESTAMP_KEY, LocalDateTime.now());
        result.put(BaseController.RESPONSE_DATA_KEY, body);

        HttpServletRequest req = ((ServletServerHttpRequest) request).getServletRequest();
        WebApplicationContext ctx = RequestContextUtils.findWebApplicationContext(req);

        if (ctx != null) {
            POCKET_CACHE = POCKET_CACHE.size() == 0
                    ? ctx.getBeansOfType(PocketProvider.class).entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
                    : POCKET_CACHE;
        }

        Map<String, Object> pocket = new HashMap<>();
        pocket.putAll(resolveSpecialPocket(returnType));
        pocket.putAll(resolveEnumPocket(returnType));
        if (pocket.size() > 0) {
            result.put(BaseController.RESPONSE_POCKET_KEY, pocket);
        }
        return result;
    }

    private Map<String, Object> resolveEnumPocket(MethodParameter returnType) {
        return Optional.ofNullable(returnType.getMethodAnnotation(EnumPocket.class))
                .<Map<String, Object>>map(e -> Arrays.stream(e.value())
                        .filter(s -> s.isEnum() && s.isAnnotationPresent(PocketName.class))
                        .map(t -> {
                            String key = t.getDeclaredAnnotation(PocketName.class).value();
                            List<SimpleItemModel<String>> value = Arrays.stream(t.getFields())
                                    .map(c -> SimpleItemModel.of(c.isAnnotationPresent(Description.class)
                                            ? c.getDeclaredAnnotation(Description.class).label()
                                            : c.getName(), c.getName()))
                                    .collect(Collectors.toList());
                            return new MutablePair<>(key, value);
                        })
                        .filter(s -> s.getRight() != null)
                        .collect(Collectors.toMap(MutablePair::getLeft, MutablePair::getRight))).orElse(new HashMap<>(0));
    }

    private Map<String, Object> resolveSpecialPocket(MethodParameter returnType) {
        return Optional.ofNullable(returnType.getMethodAnnotation(SpecialPocket.class))
                .<Map<String, Object>>map(p -> Arrays.stream(p.value())
                        .parallel()
                        .flatMap(s ->
                                POCKET_CACHE.entrySet().stream()
                                        .filter(t -> s.equals(t.getValue().getClass()))
                                        .map(t -> new MutablePair<>(t.getKey(), t.getValue().obtain())))
                        .filter(s -> s.getRight() != null)
                        .collect(Collectors.toMap(MutablePair::getLeft, MutablePair::getRight))).orElse(new HashMap<>(0));
    }

}