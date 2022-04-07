package com.czy.pulsar.core;

import com.czy.pulsar.annotation.PulsarListener;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PulsarListenerScanner implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> targetClass = AopUtils.getTargetClass(bean);
        List<ListenerMethod> methods = new ArrayList<>();
        ReflectionUtils.doWithMethods(targetClass,
                method -> {
                    Collection<PulsarListener> listenerAnnotations = findListenerAnnotations(method);
                    if (listenerAnnotations.size() > 0) {
                        methods.add(new ListenerMethod(method, listenerAnnotations.toArray(new PulsarListener[0])));
                    }
                }, ReflectionUtils.USER_DECLARED_METHODS);
        for (ListenerMethod lm : methods) {
            for (PulsarListener pulsarListener : lm.annotations) {
                processListener(pulsarListener, lm.method, bean, beanName);
            }
        }
        return bean;
    }

    public void processListener(PulsarListener pulsarListener, Method method, Object bean, String beanName) {

    }

    private Collection<PulsarListener> findListenerAnnotations(AnnotatedElement element) {
        return MergedAnnotations.from(element, MergedAnnotations.SearchStrategy.TYPE_HIERARCHY)
                .stream(PulsarListener.class)
                .map(MergedAnnotation::synthesize)
                .collect(Collectors.toList());
    }

    private static class ListenerMethod {

        final Method method;

        final PulsarListener[] annotations;

        ListenerMethod(Method method, PulsarListener[] annotations) {
            this.method = method;
            this.annotations = annotations;
        }

    }
}
