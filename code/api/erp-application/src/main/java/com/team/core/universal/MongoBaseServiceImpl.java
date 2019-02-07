package com.team.core.universal;

import com.team.entity.BaseEntity;
import com.team.entity.page.PageModel;
import com.team.repository.mongo.MongoBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class MongoBaseServiceImpl<TEntity extends BaseEntity> implements MongoBaseService<TEntity> {

    @Autowired
    protected MongoBaseRepository<TEntity, String> mongoRepository;

    @Override
    public PageModel<TEntity> SelectPageListBy(Integer pageIndex, Integer pageSize, Example<TEntity> query, Sort.Direction direction, String... properties) {
        Page<TEntity> page;
        if (direction == null) {
            direction = Sort.Direction.DESC;
        }
        if (query == null) {
            page = mongoRepository.findAll(PageRequest.of(pageIndex - 1, pageSize, direction, properties));

        } else {
            page = mongoRepository.findAll(query, PageRequest.of(pageIndex - 1, pageSize, direction, properties));
        }
        return PageModel.of(page);
    }
}
