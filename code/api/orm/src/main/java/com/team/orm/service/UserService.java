package com.team.orm.service;

import com.team.orm.entity.UserPO;
import com.team.orm.repository.UserRepository;
import org.apache.commons.collections4.map.ReferenceMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private ReferenceMap<String, Object> cache = new ReferenceMap<>();
//    private Map<String, SoftReference<Object>> cache = new HashMap<>();

    @Autowired
    UserRepository userRepository;

    public List<UserPO> findAll() {
        return userRepository.findAll();
    }

    public void put(String key, Object value) {
        cache.computeIfAbsent(key, t -> value);
        System.out.println(cache.size());
    }
}
