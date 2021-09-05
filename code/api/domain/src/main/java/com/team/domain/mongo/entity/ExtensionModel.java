package com.team.domain.mongo.entity;

import lombok.Data;

import javax.persistence.Transient;
import java.util.Map;

@Data
public class ExtensionModel {
    @Transient
    private Map<String, Object> extension;
}
