package com.team.orm.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "basic_user")
@Entity
@Data
public class UserPO {
    @Id
    private Long id;
    private String name;
    private String phoneNo;
    private String idNum;

//    private LocalDateTime createTime;
//    private LocalDateTime updateTime;
//    private String createUser;
//    private String updateUser;
//    private Boolean deleted;
}
