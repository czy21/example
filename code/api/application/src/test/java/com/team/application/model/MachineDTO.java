package com.team.application.model;

import com.team.application.kind.RinseEvent;

public class MachineDTO {
    private RinseEvent event;
    private String processId;

    public RinseEvent getEvent() {
        return event;
    }

    public void setEvent(RinseEvent event) {
        this.event = event;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }
}
