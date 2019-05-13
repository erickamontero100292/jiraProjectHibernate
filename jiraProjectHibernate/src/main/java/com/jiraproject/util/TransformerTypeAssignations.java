package com.jiraproject.util;

public enum TransformerTypeAssignations {
    STORY("Historia","Story")
    ;

    private String assignationName;
    private String transformName;

    TransformerTypeAssignations(String assignationName, String transformName) {
        this.assignationName = assignationName;
        this.transformName = transformName;
    }

    public String getAssignationName() {
        return assignationName;
    }

    public String getTransformName() {
        return transformName;
    }
}
