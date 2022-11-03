package com.team.orm.repository;

import com.team.orm.entity.UserPO;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserPO, Long> {
}
