import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
//        boolean test = true;
//        while (test) {
//            System.out.println("Welcome To Bank Management!");
//            System.out.println("1. Administration");
//            System.out.println("2. Customer");
//            System.out.println("3. Exit");
//            System.out.print("Enter Option: ");
//            int type = input.nextInt();
//            System.out.println("=========================================");
//            if (type == 1) {
//                System.out.println("Admin Login");
        Manager admin = new Manager();
        admin.login();

//            } else if (type == 2) {
//        Customer user = new Customer();
//        user.login();
//        user.registration();
//                System.out.println("Customer Login");
//            } else if (type == 3) {
//                test = false;
//                System.out.println("Exit");
//            }
//        }
    }
}
