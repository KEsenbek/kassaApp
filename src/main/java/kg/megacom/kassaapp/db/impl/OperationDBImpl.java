package kg.megacom.kassaapp.db.impl;

import kg.megacom.kassaapp.db.ConnectionDB;
import kg.megacom.kassaapp.db.OperationDB;
import kg.megacom.kassaapp.models.Operation;
import kg.megacom.kassaapp.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OperationDBImpl implements OperationDB {
    @Override
    public Operation saveOperation(Operation operation, int userID) {
        Connection connection = null;
        Operation operationFromDB = null;


        try{

            connection = ConnectionDB.INSTANCE.getConnection();
            String sql = "insert into operation (oper_date,total_price,user_id) values (?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1,operation.getAddDate().toString());
            ps.setDouble(2,operation.getTotal());
            ps.setInt(3,userID);
            ps.execute();

        operationFromDB = findOperationByTotalPriceAndOperDate(operation.getTotal(),operation.getAddDate().toString());


        }catch (Exception e) {
            e.printStackTrace();
        }
            return operationFromDB;



    }




    @Override
    public Operation findOperationByTotalPriceAndOperDate(double totalPrice, String operDate) {
        Connection connection = null;

        try {

            connection = ConnectionDB.INSTANCE.getConnection();
            String query ="select * from operation where oper_date = ? and total_price = ? ";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,operDate);
            ps.setDouble(2,totalPrice);

            ResultSet rs = ps.executeQuery();

            Operation operation = new Operation();
            operation.setId(rs.getInt(1));
            //operation.setAddDate(rs.getDate(2));
            operation.setTotal(rs.getInt(3));




            return operation;


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ConnectionDB.INSTANCE.close(connection);
        }


        return null;
    }
}
