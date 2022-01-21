package com.team.application.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaterialVO {
    private String name;
    private String url;
    private String uid;
    private String targetDataSource;

    public MaterialVO(String uid,String name, String url) {
        this.name = name;
        this.url = url;
        this.uid = uid;
    }
}
