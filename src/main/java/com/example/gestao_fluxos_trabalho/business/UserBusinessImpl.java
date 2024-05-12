package com.example.gestao_fluxos_trabalho.business;

import com.example.gestao_fluxos_trabalho.DAO.UserDAO;
import com.example.gestao_fluxos_trabalho.DTO.UsersDTO;
import com.example.gestao_fluxos_trabalho.model.users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserBusinessImpl implements UserBusiness{

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public Users createUser(Users user) {
        return userDAO.createUser(user);
    }

    @Override
    public List<UsersDTO> getAllUsersDTO() {
        List<Users> users = userDAO.findAll();
        return users.stream().map(UsersDTO::new).collect(Collectors.toList());
    }

    @Override
    public UsersDTO getUserDTO(Long id) {
        Users user = userDAO.findById(id);
        return new UsersDTO(user);

    }
}
