package com.team.mybatis.inteceptor;

import com.team.mybatis.annotation.SensitiveAction;
import com.team.mybatis.annotation.SensitiveEnum;
import com.team.mybatis.annotation.SensitiveField;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Component
@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
public class DataSafeInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object target = invocation.getTarget();
        Object[] args = invocation.getArgs();
        Object result = null;
        if (target instanceof Executor) {
            MappedStatement ms = (MappedStatement) args[0];
            String mapperFullName = ms.getId().substring(0, ms.getId().lastIndexOf("."));
            String mapperMethodName = ms.getId().substring(ms.getId().lastIndexOf(".") + 1);
            Object mapperMethodParam = args[1];
            SensitiveEnum selectParamAction = (SensitiveEnum) SensitiveAction.class.getMethod("selectParam").getDefaultValue();
            SensitiveEnum selectResultAction = (SensitiveEnum) SensitiveAction.class.getMethod("selectResult").getDefaultValue();
            SensitiveEnum updateParamAction = (SensitiveEnum) SensitiveAction.class.getMethod("updateParam").getDefaultValue();
            SensitiveAction methodSensitiveAction = Arrays.stream(Class.forName(mapperFullName).getMethods())
                    .filter(t -> t.getName().equals(mapperMethodName)).findFirst()
                    .map(t -> t.getAnnotation(SensitiveAction.class)).orElse(null);
            if (methodSensitiveAction != null) {
                selectParamAction = methodSensitiveAction.selectParam();
                selectResultAction = methodSensitiveAction.selectResult();
                updateParamAction = methodSensitiveAction.updateParam();
            }
            // handle param
            Map<String, SensitiveField> paramSafeMap = getSafeFieldMap(mapperMethodParam.getClass());
            // query
            if (args.length == 4) {
                for (Map.Entry<String, SensitiveField> e : paramSafeMap.entrySet()) {
                    setSensitiveField(selectParamAction, mapperMethodParam, e.getKey());
                }
                result = invocation.proceed();
                List<?> resultList = (List<?>) result;
                Class<?> targetClass = resultList.stream().findFirst().map(Object::getClass).orElse(null);
                if (targetClass == null) {
                    return result;
                }
                for (Object t : resultList) {
                    for (Map.Entry<String, SensitiveField> e : paramSafeMap.entrySet()) {
                        setSensitiveField(selectResultAction, t, e.getKey());
                    }
                }
            }
            // update
            if (args.length == 2) {
                for (Map.Entry<String, SensitiveField> e : paramSafeMap.entrySet()) {
                    setSensitiveField(updateParamAction, mapperMethodParam, e.getKey());
                }
                result = invocation.proceed();
            }
        }
        return result;
    }

    protected void setSensitiveField(SensitiveEnum action, Object obj, String fieldName) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Object originValue = BeanUtils.getProperty(obj, fieldName);
        Object targetValue = originValue;
        switch (action) {
            case ENCRYPT:
                targetValue = "haha";
                break;
            case DECRYPT:
                targetValue = "hoho";
                break;
            case DECRYPT_MASK:
                targetValue = "bushiz";
                break;
            case NONE:
                break;
            default:
                break;
        }
        BeanUtils.setProperty(obj, fieldName, targetValue);
    }

    private Map<String, SensitiveField> getSafeFieldMap(Class<?> targetClass) {
        Map<String, SensitiveField> safeMap = new HashMap<>(1);
        Field[] fields = targetClass.getDeclaredFields();
        for (Field field : fields) {
            SensitiveField safeFieldAnnotation = field.getAnnotation(SensitiveField.class);
            if (safeFieldAnnotation != null) {
                safeMap.put(field.getName(), safeFieldAnnotation);
            }
        }
        return safeMap;
    }

    public void setProperties(Properties properties) {
    }
}