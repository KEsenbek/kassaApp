package kg.megacom.kassaapp.db;

import kg.megacom.kassaapp.db.impl.CategoryDBImpl;
import kg.megacom.kassaapp.models.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDB {

    CategoryDB INSTANCE = new CategoryDBImpl();

    public void insert (Category category) throws SQLException;

    public  void  delete (Integer id) throws SQLException;

    public List<Category> findAll();

    public void update(Category category) throws SQLException;


}
