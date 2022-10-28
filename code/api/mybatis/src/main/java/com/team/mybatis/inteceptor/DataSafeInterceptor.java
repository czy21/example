package com.team.mybatis.inteceptor;

import com.team.mybatis.annotation.SafeField;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Component
@Intercepts({
//        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
//        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
        @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class}),
        @Signature(type = ParameterHandler.class, method = "setParameters", args = {PreparedStatement.class})
})
public class DataSafeInterceptor implements Interceptor {
    private Properties properties = new Properties();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object target = invocation.getTarget();
        Method method = invocation.getMethod();
        Object[] args = invocation.getArgs();
        Object result = invocation.proceed();
        if (target instanceof ResultSetHandler) {
            Collection<?> resultList = ((Collection<?>) result);
            Class<?> targetClass = resultList.stream().findFirst().map(Object::getClass).orElse(null);
            if (targetClass == null) {
                return result;
            }
            Map<String, SafeField> safeMap = new HashMap<>(1);
            Field[] fields = targetClass.getDeclaredFields();
            for (Field field : fields) {
                SafeField safeFieldAnnotation = field.getAnnotation(SafeField.class);
                if (safeFieldAnnotation != null) {
                    safeMap.put(field.getName(), safeFieldAnnotation);
                }
            }
            for (Object t : resultList) {
                for (Map.Entry<String, SafeField> s : safeMap.entrySet()) {
                    Object value = BeanUtils.getProperty(t, s.getKey());
                    Object decryptedValue = "decrypted value1"; // invoke SafeService
                    BeanUtils.setProperty(t, s.getKey(), decryptedValue);
                }
            }
        }
        return result;
    }

//    protected void decrypt(Object object, String propertyName) {
//
//    }

    private void decrypt(Object object) throws InvocationTargetException, IllegalAccessException {
//        Field[] fields = object.getClass().getDeclaredFields();
//        for (Field field : fields) {
//            SafeField confidential = field.getAnnotation(SafeField.class);
//            if (confidential == null) {
//                continue;
//            }
//            PropertyDescriptor ps = BeanUtils.getPropertyDescriptor(object.getClass(), field.getName());
//            if (ps.getReadMethod() == null || ps.getWriteMethod() == null) {
//                continue;
//            }
//            Object value = ps.getReadMethod().invoke(object);
//            if (value != null) {
//                ps.getWriteMethod().invoke(object, "***");
//            }
//        }
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}