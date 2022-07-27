package kg.megacom.kassaapp.services;

import kg.megacom.kassaapp.db.CategoryDB;
import kg.megacom.kassaapp.db.ProductDB;
import kg.megacom.kassaapp.models.Category;
import kg.megacom.kassaapp.models.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductService {

    private static ProductService INSTANCE;

    public static ProductService getINSTANCE() {
        if(INSTANCE == null) {
            INSTANCE = new ProductService();
        }
        return INSTANCE;
    }

    public void save (Product product) {
        if (product.getId() == null)
            ProductDB.getINSTANCE().insert(product);
        else
            ProductDB.getINSTANCE().update(product);
    }

    public List<Product> getProduct() {
        return ProductDB.getINSTANCE().selectProducts();  }


    public Product findProductByBarcode(String barcode) {
        return ProductDB.getINSTANCE().findProductByBarcode(barcode);

    }
}
