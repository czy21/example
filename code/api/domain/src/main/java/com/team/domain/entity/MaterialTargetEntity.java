package com.team.domain.entity;


import com.team.domain.kind.MaterialTargetKind;
import lombok.Data;

import java.util.Set;

@Data
public class MaterialTargetEntity {
    private String key;
    private String rootUrl;
    private String rootPath;
    private MaterialTargetKind targetKind;
    private Set<MaterialEntity> materials;
}
