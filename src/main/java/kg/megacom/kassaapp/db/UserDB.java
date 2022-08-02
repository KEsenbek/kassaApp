package kg.megacom.kassaapp.db;

import kg.megacom.kassaapp.db.impl.UserDBImpl;
import kg.megacom.kassaapp.models.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDB {

    UserDB INSTANCE = new UserDBImpl();

    void insert(User user);

    void update(User user);

    List<User> selectUsers();

    void delete(Integer id) throws SQLException;

    User searchByLoginAndPassword (String login, String password);
}
