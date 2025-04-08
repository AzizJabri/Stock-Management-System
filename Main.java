
import models.Admin;
import controllers.AdminController;
import java.util.Scanner;
public class Main {
    static void createDefaultAdmin() {
        AdminController adminController = AdminController.getInstance();
        adminController.registerAdmin("admin", "admin123", "Admin User", 22, 123456789);
    }
    public static void main(String[] args){
        createDefaultAdmin();

        Scanner scanner = new Scanner(System.in);
        AdminController adminController = AdminController.getInstance();
        Admin admin;

        while (true) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            admin = adminController.loginAdmin(username, password);
            if (admin == null) {
                System.out.println("Invalid username or password. Please try again.");
            } else {
                System.out.println("Login successful!");
                break;

            }
        }
        // application logic


    }
}
