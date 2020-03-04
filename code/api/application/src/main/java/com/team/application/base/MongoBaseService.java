package com.team.application.base;

import com.team.application.model.page.PageModel;
import com.team.infrastructure.base.BaseEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

public interface MongoBaseService<TEntity extends BaseEntity> {

    PageModel<TEntity> findAll(Integer pageIndex, Integer pageSize, Example<TEntity> example, Sort.Direction direction, String... properties);

}
