package kg.megacom.kassaapp.services.impl;

import kg.megacom.kassaapp.db.UnitDB;
import kg.megacom.kassaapp.models.Unit;
import kg.megacom.kassaapp.services.UnitService;

import java.util.List;

public class UnitServiceImpl implements UnitService {

    //private  static UnitServiceImpl INSTANCE;


    public List<Unit> getUnits () {
        return UnitService.INSTANCE.getUnits();
    }

}
