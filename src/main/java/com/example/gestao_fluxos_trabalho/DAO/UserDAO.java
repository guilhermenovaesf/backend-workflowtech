package com.example.gestao_fluxos_trabalho.DAO;

import com.example.gestao_fluxos_trabalho.model.users.Users;

import java.util.List;

public interface UserDAO {
    public Users createUser(Users user);

    public Users findById(Long id);

    public List<Users> findAll();
}
