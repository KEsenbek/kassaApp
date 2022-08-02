package kg.megacom.kassaapp.services.impl;

import kg.megacom.kassaapp.db.ProductDB;
import kg.megacom.kassaapp.db.impl.ProductDBImpl;
import kg.megacom.kassaapp.models.Product;
import kg.megacom.kassaapp.services.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    //private static ProductServiceImpl INSTANCE;



    public void save (Product product) {
        if (product.getId() == null)
            ProductDB.INSTANCE.insert(product);
        else
            ProductDB.INSTANCE.update(product);
    }

    public List<Product> getProduct() {
        return ProductDBImpl.INSTANCE.selectProducts();  }


    public Product findProductByBarcode(String barcode) {
        return ProductDBImpl.INSTANCE.findProductByBarcode(barcode);

    }
}
