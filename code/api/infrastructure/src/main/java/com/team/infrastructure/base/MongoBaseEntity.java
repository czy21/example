package com.team.infrastructure.base;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class MongoBaseEntity {
    @Id
    private String id;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    private String createdUser;

    private String modifiedUser;
}
