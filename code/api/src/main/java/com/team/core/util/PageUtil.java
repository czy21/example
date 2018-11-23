package com.team.core.util;

import lombok.Data;

import java.util.List;

@Data
public class PageUtil {

    private Integer pageIndex;
    private Integer pageSize;
    private Integer total;

    private <T> List<T> ListSplit(List<T> list) {
        total = list.size();
        return list.subList(pageSize * (pageIndex - 1), ((pageSize * pageIndex) > total ? total : (pageSize * pageIndex)));
    }

}
