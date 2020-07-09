package com.team.external.ali.model;

import lombok.Data;

@Data
public class AliAccessKeyConfig {

    private String id;
    private String secret;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}