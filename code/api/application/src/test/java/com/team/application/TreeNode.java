package com.team.application;

public class TreeNode {

    public TreeNode(String institutionId, String institutionIdAim) {
        this.institutionId = institutionId;
        this.institutionIdAim = institutionIdAim;
    }

    private String institutionId;
    private String institutionIdAim;

    public String getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(String institutionId) {
        this.institutionId = institutionId;
    }

    public String getInstitutionIdAim() {
        return institutionIdAim;
    }

    public void setInstitutionIdAim(String institutionIdAim) {
        this.institutionIdAim = institutionIdAim;
    }
}