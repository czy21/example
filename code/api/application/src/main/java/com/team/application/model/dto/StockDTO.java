package com.team.application.model.dto;

import com.team.application.model.base.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class StockDTO extends BaseDTO {
    private String id;
    private String name;
    private Long count;
    private String userId;
    private LocalDateTime submitDate;
}
