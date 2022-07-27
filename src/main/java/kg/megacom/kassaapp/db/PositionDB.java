package kg.megacom.kassaapp.db;

import kg.megacom.kassaapp.models.Category;
import kg.megacom.kassaapp.models.Position;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PositionDB {

        private static PositionDB INSTANCE;

        public static PositionDB getINSTANCE() {
            if (INSTANCE == null) {
                INSTANCE = new PositionDB();
            }
            return INSTANCE;
        }

        public void insert (Position position) throws SQLException {
            Connection connection = null;
            try {
                connection = ConnectionDB.getConnection();
                PreparedStatement ps = connection.prepareStatement("insert into positions (name) values(?)");
                ps.setString(1, position.getName());
                ps.executeUpdate();
            } finally {
                ConnectionDB.close(connection);
            }
        }

        public  void  delete (Integer id) throws SQLException {
            Connection connection = null;
            try {
                connection = ConnectionDB.getConnection();
                PreparedStatement ps = connection.prepareStatement("delete from positions where id = ?");
                ps.setInt(1,id);
                ps.executeUpdate();
            }finally {
                ConnectionDB.close(connection);
            }
        }



        public List<Position> findAll() {
            Connection connection = null;
            List<Position> positions = new ArrayList<>();
            try{
                connection = ConnectionDB.getConnection();
                PreparedStatement ps = connection.prepareStatement("select * from positions");
                ResultSet resultSet = ps.executeQuery();

                while (resultSet.next()) {
                    Position position = new Position();
                    position.setId(resultSet.getInt(1));
                    position.setName(resultSet.getString(2));
                    positions.add(position);
                }
            }catch (SQLException e){
                e.printStackTrace();
            }finally {
                ConnectionDB.close(connection);
                return positions;
            }
        }

        public void update(Position position) throws SQLException {
            Connection connection = null;
            try{
                connection = ConnectionDB.getConnection();
                PreparedStatement ps = connection.prepareStatement("update positions set name = ? where id = ?");
                ps.setString(1, position.getName());
                ps.setInt(2,position.getId());
                ps.executeUpdate();
            }finally {
                ConnectionDB.close(connection);

            }

        }
    }
