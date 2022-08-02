package kg.megacom.kassaapp.services.impl;

import kg.megacom.kassaapp.db.UserDB;
import kg.megacom.kassaapp.models.User;
import kg.megacom.kassaapp.services.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

  //  private UserDBImpl userDBImpl = UserDB.INSTANCE();

    private static UserServiceImpl INSTANCE;



    public void save (User user)
    { if (user.getId() == null)
        UserDB.INSTANCE.insert(user);
    else
        UserDB.INSTANCE.update(user);

    }

    public List<User> selectUsers() {
        return UserDB.INSTANCE.selectUsers();
    }

    public void delete (Integer id) {
        try{
            UserDB.INSTANCE.delete(id);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User searchLoginAndPassword(String login, String password)
    {return UserDB.INSTANCE.searchByLoginAndPassword(login,password);

    }
}
