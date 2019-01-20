package com.team.core.universal;

import com.team.entity.BaseEntity;
import com.team.entity.page.PageModel;
import org.springframework.data.domain.Example;

public interface MongoBaseService<TEntity extends BaseEntity> {

    PageModel<TEntity> SelectPageListBy(Integer pageIndex, Integer pageSize, Example<TEntity> query);

}
