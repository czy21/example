package com.team.cooperated.advice;

import com.team.cooperated.annotation.Description;
import com.team.cooperated.annotation.EnumOption;
import com.team.cooperated.annotation.OptionName;
import com.team.cooperated.annotation.SpecialOption;
import com.team.cooperated.controller.BaseController;
import com.team.cooperated.model.simple.SimpleItemModel;
import com.team.cooperated.option.Option;
import com.team.cooperated.option.OptionProvider;
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

    private Map<String, OptionProvider<?>> optionCache = new ConcurrentHashMap<>();

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
        pocket.putAll(resolveSpecialOption(returnType));
        pocket.putAll(resolveEnumOption(returnType));
        if (pocket.size() > 0) {
            result.put(BaseController.RESPONSE_OPTION_KEY, pocket);
        }
        return result;
    }

    private Map<String, Object> resolveEnumOption(MethodParameter returnType) {
        return Optional.ofNullable(returnType.getMethodAnnotation(EnumOption.class))
                .<Map<String, Object>>map(e -> Arrays.stream(e.value())
                        .filter(s -> s.isEnum() && s.isAnnotationPresent(OptionName.class))
                        .collect(HashMap::new, (m, n) -> {
                            String key = n.getDeclaredAnnotation(OptionName.class).value();
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

    private Map<String, Object> resolveSpecialOption(MethodParameter returnType) {
        return Optional.ofNullable(returnType.getMethodAnnotation(SpecialOption.class))
                .<Map<String, Object>>map(p -> Arrays.stream(p.value())
                        .collect(
                                HashMap::new,
                                (m, n) -> optionCache.entrySet().stream().filter(t -> t.getValue().getClass().equals(n)).forEach(t -> m.put(t.getKey(), t.getValue().obtain())),
                                Map::putAll))
                .orElse(new HashMap<>());
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        optionCache = applicationContext.getBeansOfType(OptionProvider.class).entrySet().stream().collect(
                HashMap::new,
                (m, n) -> m.put(Optional.ofNullable(n.getValue().getClass().getAnnotation(OptionName.class)).map(OptionName::value).orElse(n.getKey()), n.getValue()),
                Map::putAll
        );
    }
}