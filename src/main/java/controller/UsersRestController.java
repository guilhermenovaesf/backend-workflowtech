package controller;
import DTO.UsersDTO;
import business.UserBusiness;
import model.users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersRestController {

    @Autowired
    private UserBusiness userBusiness;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Users> createUser(@RequestBody UsersDTO userDTO) {
        Users createdUser = userBusiness.createUser(convertToEntity(userDTO));
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
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
