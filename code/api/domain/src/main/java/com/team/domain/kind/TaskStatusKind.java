package com.team.domain.kind;

public enum TaskStatusKind {
    STARTED("已开始"),
    EXECUTING("执行中"),
    FINISHED("已完成"),
    FAIL("失败");

    private String label;

    TaskStatusKind(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
