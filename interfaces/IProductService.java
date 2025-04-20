package interfaces;

import models.Product;
import java.util.ArrayList;

public interface IProductService {
    void addProduct(int category_id, String name, String description, float price,
                    int quantity_in_stock, int supplier_id);

    void updateProduct(Product product);

    void deleteProduct(int id);

    Product getProductById(int id);

    ArrayList<Product> listProducts();
}

