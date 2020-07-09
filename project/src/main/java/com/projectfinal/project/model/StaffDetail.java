package com.projectfinal.project.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name = "StaffDetail")
public class StaffDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String staff_id;

    @Column(nullable = false)
    private String staff_first_name;

    @Column(nullable = false)
    private String staff_last_name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String avatar;

    @Column(nullable = false)
    private Timestamp create_at;

    @Column(nullable = false)
    private Timestamp update_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getStaff_first_name() {
        return staff_first_name;
    }

    public void setStaff_first_name(String staff_first_name) {
        this.staff_first_name = staff_first_name;
    }

    public String getStaff_last_name() {
        return staff_last_name;
    }

    public void setStaff_last_name(String staff_last_name) {
        this.staff_last_name = staff_last_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Timestamp getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Timestamp create_at) {
        this.create_at = create_at;
    }

    public Timestamp getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Timestamp update_at) {
        this.update_at = update_at;
    }
}
