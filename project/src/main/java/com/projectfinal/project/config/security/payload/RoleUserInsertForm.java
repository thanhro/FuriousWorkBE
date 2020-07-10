package com.projectfinal.project.config.security.payload;

import javax.validation.constraints.NotBlank;

public class RoleUserInsertForm {

    @NotBlank
    private String role_name;

    private String description;

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
