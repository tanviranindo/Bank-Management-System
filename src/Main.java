import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("============================================================================");
        System.out.println("WELCOME TO BANK MANAGEMENT SYSTEM!");
        System.out.println("============================================================================");
        boolean test = true;
        while (test) {
            System.out.println("HOME");
            System.out.println("1. Administration");
            System.out.println("2. Customer");
            System.out.println("0. Exit");
            System.out.print("Enter Option: ");
            int type = input.nextInt();
            if (type == 1) {
                System.out.println("============================================================================");
                System.out.println("ADMIN PANEL");
                Manager admin = new Manager();
                admin.login();
            } else if (type == 2) {
                System.out.println("============================================================================");
                System.out.println("USER PANEL");
                System.out.println("1. Login");
                System.out.println("2. Registration");
                System.out.println("0. Back");
                System.out.print("Enter Option: ");
                int option = input.nextInt();
                System.out.println("============================================================================");
                if (option == 1) {
                    User profile = new User();
                    profile.login();
                } else if (option == 2) {
                    Customer profile = new Customer();
                    profile.registration();
                } else if (option == 0) {
                    System.out.println("HEADING TO HOME");
                    System.out.println("============================================================================");
                } else {
                    System.out.println("============================================================================");
                    System.out.println("Wrong Option Selected! Please try again.");
                    System.out.println("============================================================================");
                }
            } else if (type == 0) {
                test = false;
                System.out.println("============================================================================");
                System.out.println("Exit");
                System.out.println("============================================================================");
            } else {
                System.out.println("============================================================================");
                System.out.println("Wrong Option Selected! Please try again.");
                System.out.println("============================================================================");
            }
        }
    }
}
