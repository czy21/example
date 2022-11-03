package com.team.orm.inteceptor;

import com.team.orm.annotation.SensitiveAction;
import com.team.orm.annotation.SensitiveEnum;
import com.team.orm.annotation.SensitiveField;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ReflectionUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;

@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
public class DataSecurityInterceptor implements Interceptor {

    Logger logger = LoggerFactory.getLogger(DataSecurityInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        Object result = null;

        MappedStatement ms = (MappedStatement) args[0];
        String mapperFullName = ms.getId().substring(0, ms.getId().lastIndexOf("."));
        String mapperMethodName = ms.getId().substring(ms.getId().lastIndexOf(".") + 1);
        Object mapperMethodParam = args[1];
        SensitiveEnum selectParamAction = (SensitiveEnum) SensitiveAction.class.getMethod("selectParam").getDefaultValue();
        SensitiveEnum selectResultAction = (SensitiveEnum) SensitiveAction.class.getMethod("selectResult").getDefaultValue();
        SensitiveEnum updateParamAction = (SensitiveEnum) SensitiveAction.class.getMethod("updateParam").getDefaultValue();
        Method mapperMethod = ms.getConfiguration()
                .getMapperRegistry().getMappers().stream()
                .filter(t -> t.getName().equals(mapperFullName))
                .flatMap(t -> Arrays.stream(t.getMethods()))
                .filter(t -> t.getName().equals(mapperMethodName))
                .findFirst().orElse(null);
        SensitiveAction methodSensitiveAction = Optional.ofNullable(mapperMethod)
                .map(t -> t.getAnnotation(SensitiveAction.class)).orElse(null);
        if (methodSensitiveAction != null) {
            selectParamAction = methodSensitiveAction.selectParam();
            selectResultAction = methodSensitiveAction.selectResult();
            updateParamAction = methodSensitiveAction.updateParam();
        }
        // query
        if (args.length == 4) {
            setSensitiveField(selectParamAction, mapperMethodParam);
            result = invocation.proceed();
            setSensitiveField(selectResultAction, result);
        }
        // update
        if (args.length == 2) {
            setSensitiveField(updateParamAction, mapperMethodParam);
            result = invocation.proceed();
        }
        return result;
    }

    private void setSensitiveField(SensitiveEnum action, Object obj) {
        if (obj instanceof List) {
            List<?> objects = ((List<?>) obj);
            Class<?> targetClass = objects.stream().findFirst().map(Object::getClass).orElse(null);
            if (targetClass == null) {
                return;
            }
            for (Object t : objects) {
                setSensitiveField(action, t);
            }
        } else if (obj instanceof Map) {
            if (obj instanceof MapperMethod.ParamMap<?>) {
                MapperMethod.ParamMap<?> paramMap = (MapperMethod.ParamMap<?>) obj;
                for (Map.Entry<String, ?> pm : paramMap.entrySet()) {
                    if (pm.getKey().startsWith("param")) {
                        setSensitiveField(action, pm.getValue());
                    }
                }
            }
        } else {
            setSensitiveFieldByFields(action, obj, getListenerFieldMap(obj.getClass()));
        }
    }

    protected void setSensitiveFieldByFields(SensitiveEnum action, Object obj, Map<String, ListenerField> listenerFieldMap) {
        switch (action) {
            case ENCRYPT:
                encrypt(obj, listenerFieldMap);
                break;
            case DECRYPT:
                decrypt(obj, listenerFieldMap);
                break;
            case NONE:
                break;
            default:
                break;
        }
    }

    protected void encrypt(Object obj, Map<String, ListenerField> sensitiveFieldMap) {

    }

    protected void decrypt(Object obj, Map<String, ListenerField> sensitiveFieldMap) {

    }

    private Map<String, ListenerField> getListenerFieldMap(Class<?> targetClass) {
        Map<String, ListenerField> fields = new HashMap<>();
        ReflectionUtils.doWithFields(targetClass, f -> {
            SensitiveField safeFieldAnnotation = f.getAnnotation(SensitiveField.class);
            if (safeFieldAnnotation != null) {
                fields.put(f.getName(), new ListenerField(safeFieldAnnotation, BeanUtils.getPropertyDescriptor(targetClass, f.getName())));
            }
        });
        return fields;
    }

    public void setProperties(Properties properties) {
    }

    @Data
    @AllArgsConstructor
    public static class ListenerField {
        SensitiveField annotation;
        PropertyDescriptor propertyDescriptor;
    }
}