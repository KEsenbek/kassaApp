package kg.megacom.kassaapp.db;

import kg.megacom.kassaapp.db.impl.ProductDBImpl;
import kg.megacom.kassaapp.models.Product;

import java.util.List;

public interface ProductDB {

    ProductDB INSTANCE = new ProductDBImpl();

    void insert(Product product);

    void update(Product product);

    List<Product> selectProducts();

    Product findProductByBarcode(String barcode);
}
