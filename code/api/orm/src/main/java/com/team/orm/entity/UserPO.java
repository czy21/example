package com.team.orm.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


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
