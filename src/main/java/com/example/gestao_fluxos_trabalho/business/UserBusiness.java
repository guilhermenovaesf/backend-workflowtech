package com.example.gestao_fluxos_trabalho.business;

import com.example.gestao_fluxos_trabalho.DTO.UsersDTO;
import com.example.gestao_fluxos_trabalho.model.users.Users;

import java.util.List;

public interface UserBusiness {
    public Users createUser(Users user);

    public List<UsersDTO> getAllUsersDTO();

    public UsersDTO getUserDTO(Long id);

    public Users findByEmailAndPassword(String email, String password);
}
