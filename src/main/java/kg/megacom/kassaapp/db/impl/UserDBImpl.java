package kg.megacom.kassaapp.db.impl;

import kg.megacom.kassaapp.db.ConnectionDB;
import kg.megacom.kassaapp.db.UserDB;
import kg.megacom.kassaapp.models.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDBImpl implements UserDB {



    public void insert(User user) {
        Connection connection = null;

        try {
            connection = ConnectionDB.INSTANCE.getConnection();
            String sql = "insert into users(name, login, password, position_id)" +
                    "values(?, ?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, user.getName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getPosition().getId());


            ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.INSTANCE.close(connection);
        }
    }

    public void update(User user) {
        Connection connection = null;

        try {
            connection = ConnectionDB.INSTANCE.getConnection();
            String sql = "update users set name = ?, login = ?, password= ?, position_id= ? where id = ?";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, user.getName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getPosition().getId());
            ps.setInt(5, user.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.INSTANCE.close(connection);
        }
    }

    public List<User> selectUsers() {
        Connection connection = null;
        List<User> users = new ArrayList<>();

        try {
            connection = ConnectionDB.INSTANCE.getConnection();

            PreparedStatement ps = connection.prepareStatement("select u.id, u.name, u.login, u.password, u.position_id, p.id, p.name from users u\n" +
                    "join positions p\n" +
                    "on u.position_id = p.id");

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setLogin(resultSet.getString(3));
                user.setPassword(resultSet.getString(4));


                Position position = new Position();
                position.setId(resultSet.getInt(5));
                position.setId(resultSet.getInt(6));
                position.setName(resultSet.getString(7));

                user.setPosition(position);

                users.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.INSTANCE.close(connection);
            return users;
        }
    }


    public void delete(Integer id) throws SQLException{
            Connection connection = null;
            try {
                connection = ConnectionDB.INSTANCE.getConnection();
                PreparedStatement ps = connection.prepareStatement("delete from users where id = ?");
                ps.setInt(1,id);
                ps.executeUpdate();
            }finally {
                ConnectionDB.INSTANCE.close(connection);
            }
        }

    @Override
    public User searchByLoginAndPassword(String login, String password) {

        Connection connection = null;
        User user = null;
        try {
            connection = ConnectionDB.INSTANCE.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from users where login =?");
            ps.setString(1, login);

            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.INSTANCE.close(connection);
        }
        return user;
    }
}

