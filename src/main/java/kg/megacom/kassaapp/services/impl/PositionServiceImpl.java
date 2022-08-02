package kg.megacom.kassaapp.services.impl;

import kg.megacom.kassaapp.db.PositionDB;
import kg.megacom.kassaapp.db.impl.PositionDBImpl;
import kg.megacom.kassaapp.models.Position;
import kg.megacom.kassaapp.services.PositionService;

import java.sql.SQLException;
import java.util.List;

public class PositionServiceImpl implements PositionService {

    public void save (Position position) throws SQLException
    { if (position.getId() == null)
        PositionDB.INSTANCE.insert(position);
    else
        PositionDB.INSTANCE.update(position);

    }

    public List<Position> getPosition() {
        return PositionDB.INSTANCE.findAll();
    }

    public void delete (Integer id) {
        try{
            PositionDB.INSTANCE.delete(id);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
