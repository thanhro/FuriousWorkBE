package com.projectfinal.project.config.security.payload;

import javax.validation.constraints.NotBlank;

public class RoleForm {

    @NotBlank
    private String role_name;

    @NotBlank
    private String description;

    @NotBlank
    private int id;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
