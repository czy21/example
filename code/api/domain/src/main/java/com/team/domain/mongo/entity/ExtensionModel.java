package com.team.domain.mongo.entity;

import jakarta.persistence.Transient;
import lombok.Data;

import java.util.Map;

@Data
public class ExtensionModel {
    @Transient
    private Map<String, Object> extension;
}
