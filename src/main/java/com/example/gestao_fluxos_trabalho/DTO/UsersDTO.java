package com.example.gestao_fluxos_trabalho.DTO;

import com.example.gestao_fluxos_trabalho.model.users.Users;

public class UsersDTO {

    private Long id;
    private String name;
    private String password;
    private String email;
    private int admin;

    // Constructors, getters, and setters

    public UsersDTO() {
    }

    public UsersDTO(Users user) {
        this.id = user.getId();
        this.name = user.getName();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.admin = user.getAdmin();
    }

    public UsersDTO(Long id, String name, String password, String email, int admin) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.admin = admin;
    }

    // getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
