package kg.megacom.kassaapp.services;

import kg.megacom.kassaapp.db.CategoryDB;
import kg.megacom.kassaapp.db.UserDB;
import kg.megacom.kassaapp.models.Category;
import kg.megacom.kassaapp.models.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    private UserDB userDB = UserDB.getINSTANCE();

    private static UserService INSTANCE;

    public static  UserService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserService();
        }
        return INSTANCE;
    }

    public void save (User user)
    { if (user.getId() == null)
        UserDB.getINSTANCE().insert(user);
    else
        UserDB.getINSTANCE().update(user);

    }

    public List<User> selectUsers() {
        return userDB.selectUsers();
    }

    public void delete (Integer id) {
        try{
            UserDB.getINSTANCE().delete(id);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
