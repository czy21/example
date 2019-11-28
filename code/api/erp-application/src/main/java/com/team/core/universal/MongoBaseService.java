package com.team.core.universal;

import com.team.entity.page.PageModel;
import com.team.domain.infrastructure.MongoBaseEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

public interface MongoBaseService<TEntity extends MongoBaseEntity> {

    PageModel<TEntity> findAll(Integer pageIndex, Integer pageSize, Example<TEntity> example, Sort.Direction direction, String... properties);

}
