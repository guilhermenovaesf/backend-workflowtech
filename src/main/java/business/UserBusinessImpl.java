package business;

import DAO.UserDAO;
import model.users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserBusinessImpl implements UserBusiness{

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public Users createUser(Users user) {
        return userDAO.createUser(user);
    }
}
