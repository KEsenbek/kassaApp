package kg.megacom.kassaapp.services;

import kg.megacom.kassaapp.db.impl.PositionDBImpl;
import kg.megacom.kassaapp.models.Position;
import kg.megacom.kassaapp.services.impl.PositionServiceImpl;

import java.sql.SQLException;
import java.util.List;

public interface PositionService {

    PositionService INSTANCE = new PositionServiceImpl();

    void save (Position position) throws SQLException;

    List<Position> getPosition();

    void delete (Integer id);
}
