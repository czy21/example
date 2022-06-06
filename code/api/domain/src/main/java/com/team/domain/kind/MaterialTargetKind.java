package com.team.domain.kind;

public enum MaterialTargetKind {

    OSS("对象存储"),
    LOCAL("本地存储");

    private String label;

    MaterialTargetKind(String comment) {
        this.label = comment;
    }

    public String getComment() {
        return label;
    }
}
