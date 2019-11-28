package com.team.application.core.universal;

import com.team.application.model.page.PageModel;
import com.team.domain.infrastructure.base.MongoBaseEntity;
import com.team.domain.infrastructure.base.MongoBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class MongoBaseServiceImpl<TEntity extends MongoBaseEntity> implements MongoBaseService<TEntity> {

    @Autowired
    protected MongoBaseRepository<TEntity, String> mongoRepository;

    @Override
    public PageModel<TEntity> findAll(Integer pageIndex, Integer pageSize, Example<TEntity> example, Sort.Direction direction, String... properties) {
        Page<TEntity> page;
        if (direction == null) {
            direction = Sort.Direction.DESC;
        }
        if (example == null) {
            page = mongoRepository.findAll(PageRequest.of(pageIndex - 1, pageSize, direction, properties));

        } else {
            page = mongoRepository.findAll(example, PageRequest.of(pageIndex - 1, pageSize, direction, properties));
        }
        return PageModel.of(page);
    }
}
