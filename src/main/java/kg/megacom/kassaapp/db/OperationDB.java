package kg.megacom.kassaapp.db;

import kg.megacom.kassaapp.db.impl.OperationDBImpl;
import kg.megacom.kassaapp.models.Operation;
import kg.megacom.kassaapp.models.User;

public interface OperationDB {

    OperationDB INSTANCE = new OperationDBImpl();
    Operation saveOperation(Operation operation, int userID);

    Operation findOperationByTotalPriceAndOperDate (double totalPrice, String operDate);
}
