package com.team.application.model;

import com.team.application.kind.RinseNode;

public class MachineDTO {
    private RinseNode node;
    private String processId;

    public RinseNode getNode() {
        return node;
    }

    public void setNode(RinseNode node) {
        this.node = node;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }
}
