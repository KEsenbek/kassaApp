package kg.megacom.kassaapp.db;

import kg.megacom.kassaapp.db.impl.PositionDBImpl;
import kg.megacom.kassaapp.models.Position;

import java.sql.SQLException;
import java.util.List;

public interface PositionDB {

    PositionDB INSTANCE = new PositionDBImpl() ;

    void insert (Position position) throws SQLException;
    void  delete (Integer id) throws SQLException;

    List<Position> findAll();

    void update(Position position) throws SQLException;

}
