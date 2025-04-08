package interfaces;

import models.Admin;

import java.util.ArrayList;

public interface IAdminService {
    // Les fonctions a implementer en SQL (CRUD)
    ArrayList<Admin> list();
    void add (Admin admin);
    Admin getById (int id);
    Admin getByUsername (String username);
    void update (int id, Admin admin);
    void delete (int id);
}
