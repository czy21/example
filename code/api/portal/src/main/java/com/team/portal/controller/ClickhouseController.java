package com.team.portal.controller;

import com.team.cooperated.controller.BaseController;
import com.team.infrastructure.datasource.DynamicDataSourceContext;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "ck")
public class ClickHouseController extends BaseController {

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @PostMapping(path = "search")
    public List<Map<String, Object>> search() throws SQLException {
        DynamicDataSourceContext.put("ds2");
        SqlSession sqlSession = sqlSessionFactory.openSession();
        String sql = "select * from ent_sale";
        return sqlSession.selectList("com.team.domain.mapper.RepositoryMapper.select", Map.of("sql", sql));
    }
}
