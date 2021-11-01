package com.team.cooperated.advice;

import com.team.cooperated.annotation.Description;
import com.team.cooperated.annotation.EnumPocket;
import com.team.cooperated.annotation.PocketName;
import com.team.cooperated.annotation.SpecialPocket;
import com.team.cooperated.controller.BaseController;
import com.team.cooperated.model.simple.SimpleItemModel;
import com.team.cooperated.pocket.Option;
import com.team.cooperated.pocket.PocketProvider;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@ControllerAdvice(assignableTypes = BaseController.class)
public class ResponseAdvice implements ResponseBodyAdvice<Object>, ApplicationContextAware {

    private Map<String, PocketProvider<?>> pocketCache = new ConcurrentHashMap<>();

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
                        .collect(HashMap::new, (m, n) -> {
                            String key = n.getDeclaredAnnotation(PocketName.class).value();
                            List<SimpleItemModel<?>> value;
                            if (Arrays.stream(n.getInterfaces()).anyMatch(i -> i == Option.class)) {
                                value = Arrays.stream(n.getEnumConstants())
                                        .map(c -> {
                                            var o = (Option<?>) c;
                                            return SimpleItemModel.of(o.getLabel(), o.getValue());
                                        })
                                        .collect(Collectors.toList());
                            } else {
                                value = Arrays.stream(n.getFields())
                                        .map(c -> SimpleItemModel.of(c.isAnnotationPresent(Description.class)
                                                ? c.getDeclaredAnnotation(Description.class).label()
                                                : c.getName(), c.getName()))
                                        .collect(Collectors.toList());
                            }
                            m.put(key, value);
                        }, Map::putAll)).orElse(new HashMap<>());
    }

    private Map<String, Object> resolveSpecialPocket(MethodParameter returnType) {
        return Optional.ofNullable(returnType.getMethodAnnotation(SpecialPocket.class))
                .<Map<String, Object>>map(p -> Arrays.stream(p.value())
                        .collect(
                                HashMap::new,
                                (m, n) -> pocketCache.entrySet().stream().filter(t -> t.getValue().getClass().equals(n)).forEach(t -> m.put(t.getKey(), t.getValue().obtain())),
                                Map::putAll))
                .orElse(new HashMap<>());
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        pocketCache = applicationContext.getBeansOfType(PocketProvider.class).entrySet().stream().collect(
                HashMap::new,
                (m, n) -> m.put(Optional.ofNullable(n.getValue().getClass().getAnnotation(PocketName.class)).map(PocketName::value).orElse(n.getKey()), n.getValue()),
                Map::putAll
        );
    }
}