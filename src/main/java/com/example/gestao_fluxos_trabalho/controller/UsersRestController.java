package com.example.gestao_fluxos_trabalho.controller;
import com.example.gestao_fluxos_trabalho.DTO.UsersDTO;
import com.example.gestao_fluxos_trabalho.business.UserBusiness;
import com.example.gestao_fluxos_trabalho.model.users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UsersRestController {

    @Autowired
    private UserBusiness userBusiness;

    @PostMapping("/create")
    public ResponseEntity<Users> createUser(@RequestBody UsersDTO userDTO) {
        Users createdUser = userBusiness.createUser(convertToEntity(userDTO));
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<UsersDTO>> listUsers() {
        List<UsersDTO> users = userBusiness.getAllUsersDTO();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersDTO> getUser(@PathVariable Long id) {
        UsersDTO user = userBusiness.getUserDTO(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UsersDTO userDTO) {
        Users user = userBusiness.findByEmailAndPassword(userDTO.getEmail(), userDTO.getPassword());
        if (user != null) {
            return new ResponseEntity<>(user.getId(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid email or password", HttpStatus.UNAUTHORIZED);
        }
    }

    private Users convertToEntity(UsersDTO userDTO) {
        Users user = new Users();
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setAdmin(userDTO.getAdmin());
        return user;
    }
}
