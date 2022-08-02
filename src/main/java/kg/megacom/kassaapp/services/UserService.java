package kg.megacom.kassaapp.services;

import kg.megacom.kassaapp.models.User;
import kg.megacom.kassaapp.services.impl.UserServiceImpl;

import java.util.List;

public interface UserService {

    UserService INSTANCE = new UserServiceImpl();
    void save (User user);
    List<User> selectUsers();

    void delete (Integer id);

    User searchLoginAndPassword(String login, String password);


}
