package com.team.core.universal;

import com.team.entity.MybatisBaseEntity;
import com.team.entity.page.PageModel;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

public interface MongoBaseService<TEntity extends MybatisBaseEntity> {

    PageModel<TEntity> SelectPageListBy(Integer pageIndex, Integer pageSize, Example<TEntity> example, Sort.Direction direction, String... properties);

}
