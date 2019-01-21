package com.team.core.universal;

import com.team.entity.BaseEntity;
import com.team.entity.page.PageModel;
import com.team.repository.mongo.MongoBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public class MongoBaseServiceImpl<TEntity extends BaseEntity> implements MongoBaseService<TEntity> {

    @Autowired
    protected MongoBaseRepository<TEntity, String> mongoRepository;

    @Override
    public PageModel<TEntity> SelectPageListBy(Integer pageIndex, Integer pageSize, Example<TEntity> query) {
        List<TEntity> list = mongoRepository.findAll(PageRequest.of(pageIndex - 1, pageSize)).getContent();
        return PageModel.of(pageIndex, pageSize, list);
    }
}
