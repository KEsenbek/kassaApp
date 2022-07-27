package kg.megacom.kassaapp.services;

import kg.megacom.kassaapp.db.CategoryDB;
import kg.megacom.kassaapp.db.PositionDB;
import kg.megacom.kassaapp.models.Category;
import kg.megacom.kassaapp.models.Position;

import java.sql.SQLException;
import java.util.List;

public class PositionService {
    private PositionDB positionDB = PositionDB.getINSTANCE();

    private static PositionService INSTANCE;

    public static  PositionService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PositionService();
        }
        return INSTANCE;
    }

    public void save (Position position) throws SQLException
    { if (position.getId() == null)
        positionDB.insert(position);
    else
        positionDB.update(position);

    }

    public List<Position> getPosition() {
        return positionDB.findAll();
    }

    public void delete (Integer id) {
        try{
            positionDB.delete(id);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
