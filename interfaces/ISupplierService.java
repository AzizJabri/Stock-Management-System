package interfaces;
import models.Supplier ;

import java.util.ArrayList;

public interface ISupplierService {
    
    void addSupplier(String name, String phone , String address);

    void updateSupplier(Supplier supplier);

    void deleteSupplier(int id);

    Supplier getSupplierById(int id);

    ArrayList<Supplier> listSuppliers();
}
