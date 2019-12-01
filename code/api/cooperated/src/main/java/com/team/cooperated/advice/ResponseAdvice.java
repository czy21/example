package com.team.cooperated.advice;

import com.team.cooperated.annotation.Description;
import com.team.cooperated.annotation.EnumPocket;
import com.team.cooperated.annotation.PocketName;
import com.team.cooperated.annotation.SpecialPocket;
import com.team.cooperated.controller.BaseController;
import com.team.cooperated.model.simple.SimpleItemModel;
import com.team.cooperated.pocket.PocketProvider;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
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

    private static Map<String, PocketProvider> POCKET_CACHE = new ConcurrentHashMap<>();

    @Override
    public boolean supports(@NotNull MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return converterType.isAssignableFrom(MappingJackson2HttpMessageConverter.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, @NotNull MethodParameter returnType, @NotNull MediaType selectedContentType, @NotNull Class<? extends HttpMessageConverter<?>> selectedConverterType, @NotNull ServerHttpRequest request, @NotNull ServerHttpResponse response) {
        Map<String, Object> result = new HashMap<>();
        result.put(BaseController.RESPONSE_TIMESTAMP_KEY, LocalDateTime.now());
        result.put(BaseController.RESPONSE_DATA_KEY, body);

        HttpServletRequest req = ((ServletServerHttpRequest) request).getServletRequest();
        WebApplicationContext ctx = RequestContextUtils.findWebApplicationContext(req);

        if (ctx != null) {
            POCKET_CACHE = POCKET_CACHE.size() == 0 ? ctx.getBeansOfType(PocketProvider.class) : POCKET_CACHE;
        }

        Map<String, Object> pocket = new HashMap<>();

        Map<String, Object> specialPockets = resolveSpecialPocket(returnType);
        Map<String, Object> enumPockets = resolveReturnEnumKeys(returnType);
        if (specialPockets.size() > 0) {
            pocket.putAll(specialPockets);
        }
        if (enumPockets.size() > 0) {
            pocket.putAll(enumPockets);
        }
        if (pocket.size() > 0) {
            result.put(BaseController.RESPONSE_POCKET_KEY, pocket);
        }
        return result;
    }

    private Map<String, Object> resolveReturnEnumKeys(MethodParameter returnType) {
        return Optional.ofNullable(returnType.getAnnotatedElement().getAnnotation(EnumPocket.class))
                .<Map<String, Object>>map(enumPocket -> Arrays.stream(enumPocket.value())
                        .filter(s -> s.isEnum() && s.isAnnotationPresent(PocketName.class))
                        .map(t -> Map.of(t.getDeclaredAnnotation(PocketName.class).value(), assembleEnumPocketValue(t)))
                        .collect(HashMap::new, HashMap::putAll, HashMap::putAll)).orElse(Map.of());
    }


    public static List<SimpleItemModel<String>> assembleEnumPocketValue(Class<?> clazz) {
        return Arrays.stream(clazz.getFields())
                .map(c -> c.isAnnotationPresent(Description.class) ? SimpleItemModel.of(c.getDeclaredAnnotation(Description.class).label(), c.getName()) : SimpleItemModel.of(c.getName(), c.getName()))
                .collect(Collectors.toList());
    }

    private Map<String, Object> resolveSpecialPocket(MethodParameter returnType) {
        return Optional.ofNullable(returnType.getAnnotatedElement().getAnnotation(SpecialPocket.class))
                .<Map<String, Object>>map(p -> Arrays.stream(p.value())
                        .flatMap(s ->
                                POCKET_CACHE.entrySet()
                                        .stream()
                                        .filter(t -> s.equals(t.getValue().getClass()))
                                        .map(t -> Map.of(t.getKey(), Optional.ofNullable(t.getValue().obtain()).orElseGet(ArrayList::new)))
                        ).collect(HashMap::new, HashMap::putAll, HashMap::putAll))
                .orElse(Map.of());
    }

}