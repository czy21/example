package com.team.configure;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.TableInfoHelper;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeException;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class MybatisDefaultParameterHandler extends DefaultParameterHandler {

    private final TypeHandlerRegistry typeHandlerRegistry;
    private final MappedStatement mappedStatement;
    private final Object parameterObject;
    private final BoundSql boundSql;
    private final Configuration configuration;

    public MybatisDefaultParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql) {
        super(mappedStatement, processBatch(mappedStatement, parameterObject), boundSql);
        this.mappedStatement = mappedStatement;
        this.configuration = mappedStatement.getConfiguration();
        this.typeHandlerRegistry = mappedStatement.getConfiguration().getTypeHandlerRegistry();
        this.parameterObject = parameterObject;
        this.boundSql = boundSql;
    }

    protected static Object processBatch(MappedStatement ms, Object parameterObject) {
        if (null == parameterObject) {
            return null;
        } else {
            MetaObjectHandler metaObjectHandler = GlobalConfigUtils.getMetaObjectHandler(ms.getConfiguration());
            boolean isFill = false;
            boolean isInsert = false;
            if (ms.getSqlCommandType() == SqlCommandType.INSERT) {
                isFill = true;
                isInsert = true;
            } else if (ms.getSqlCommandType() == SqlCommandType.UPDATE && metaObjectHandler != null && metaObjectHandler.openUpdateFill()) {
                isFill = true;
            }

            if (isFill) {
                Collection<Object> parameters = getParameters(parameterObject);
                Object et;
                if (null != parameters) {
                    List<Object> objList = new ArrayList();
                    Iterator var10 = parameters.iterator();

                    while (var10.hasNext()) {
                        et = var10.next();
                        TableInfo tableInfo = TableInfoHelper.getTableInfo(et.getClass());
                        if (null != tableInfo) {
                            objList.add(populateKeys(metaObjectHandler, tableInfo, ms, et, isInsert));
                        } else {
                            objList.add(et);
                        }
                    }

                    return objList;
                } else {
                    TableInfo tableInfo = null;
                    if (parameterObject instanceof Map) {
                        Map map = (Map) parameterObject;
                        if (map.containsKey("et")) {
                            et = map.get("et");
                            if (et != null) {
                                if (et instanceof Map) {
                                    Map realEtMap = (Map) et;
                                    if (realEtMap.containsKey("MP_OPTLOCK_ET_ORIGINAL")) {
                                        tableInfo = TableInfoHelper.getTableInfo(realEtMap.get("MP_OPTLOCK_ET_ORIGINAL").getClass());
                                    }
                                } else {
                                    tableInfo = TableInfoHelper.getTableInfo(et.getClass());
                                }
                            }
                        }
                    } else {
                        tableInfo = TableInfoHelper.getTableInfo(parameterObject.getClass());
                    }

                    return populateKeys(metaObjectHandler, tableInfo, ms, parameterObject, isInsert);
                }
            } else {
                return parameterObject;
            }
        }
    }

    protected static Collection<Object> getParameters(Object parameter) {
        Collection<Object> parameters = null;
        if (parameter instanceof Collection) {
            parameters = (Collection) parameter;
        } else if (parameter instanceof Map) {
            Map parameterMap = (Map) parameter;
            if (parameterMap.containsKey("collection")) {
                parameters = (Collection) parameterMap.get("collection");
            } else if (parameterMap.containsKey("list")) {
                parameters = (List) parameterMap.get("list");
            } else if (parameterMap.containsKey("array")) {
                parameters = Arrays.asList((Object[]) ((Object[]) parameterMap.get("array")));
            }
        }

        return (Collection) parameters;
    }

    protected static Object populateKeys(MetaObjectHandler metaObjectHandler, TableInfo tableInfo, MappedStatement ms, Object parameterObject, boolean isInsert) {
        if (null == tableInfo) {
            return parameterObject;
        } else {
            MetaObject metaObject = ms.getConfiguration().newMetaObject(parameterObject);
            if (isInsert && !StringUtils.isEmpty(tableInfo.getKeyProperty()) && null != tableInfo.getIdType() && tableInfo.getIdType().getKey() >= 3) {
                Object idValue = metaObject.getValue(tableInfo.getKeyProperty());
                if (StringUtils.checkValNull(idValue)) {
                    if (tableInfo.getIdType() == IdType.ID_WORKER) {
                        metaObject.setValue(tableInfo.getKeyProperty(), UidGenService.getUid());
                    } else if (tableInfo.getIdType() == IdType.ID_WORKER_STR) {
                        metaObject.setValue(tableInfo.getKeyProperty(), String.valueOf(UidGenService.getUid()));
                    } else if (tableInfo.getIdType() == IdType.UUID) {
                        metaObject.setValue(tableInfo.getKeyProperty(), IdWorker.get32UUID());
                    }
                }
            }

            if (metaObjectHandler != null) {
                if (isInsert && metaObjectHandler.openInsertFill()) {
                    metaObjectHandler.insertFill(metaObject);
                } else if (!isInsert) {
                    metaObjectHandler.updateFill(metaObject);
                }
            }

            return metaObject.getOriginalObject();
        }
    }

    public void setParameters(PreparedStatement ps) {
        ErrorContext.instance().activity("setting parameters").object(this.mappedStatement.getParameterMap().getId());
        List<ParameterMapping> parameterMappings = this.boundSql.getParameterMappings();
        if (parameterMappings != null) {
            for (int i = 0; i < parameterMappings.size(); ++i) {
                ParameterMapping parameterMapping = (ParameterMapping) parameterMappings.get(i);
                if (parameterMapping.getMode() != ParameterMode.OUT) {
                    String propertyName = parameterMapping.getProperty();
                    Object value;
                    if (this.boundSql.hasAdditionalParameter(propertyName)) {
                        value = this.boundSql.getAdditionalParameter(propertyName);
                    } else if (this.parameterObject == null) {
                        value = null;
                    } else if (this.typeHandlerRegistry.hasTypeHandler(this.parameterObject.getClass())) {
                        value = this.parameterObject;
                    } else {
                        MetaObject metaObject = this.configuration.newMetaObject(this.parameterObject);
                        value = metaObject.getValue(propertyName);
                    }

                    TypeHandler typeHandler = parameterMapping.getTypeHandler();
                    JdbcType jdbcType = parameterMapping.getJdbcType();
                    if (value == null && jdbcType == null) {
                        jdbcType = this.configuration.getJdbcTypeForNull();
                    }

                    try {
                        typeHandler.setParameter(ps, i + 1, value, jdbcType);
                    } catch (SQLException | TypeException var10) {
                        throw new TypeException("Could not set parameters for mapping: " + parameterMapping + ". Cause: " + var10, var10);
                    }
                }
            }
        }

    }
}
