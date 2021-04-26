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
            String type = input.nextLine();
            switch (type) {
                case "1" -> {
                    System.out.println("============================================================================");
                    System.out.println("ADMIN PANEL [CASE SENSITIVE]");
                    Manager admin = new Manager(); //Login into manager panel
                    admin.login();
                }
                case "2" -> {
                    System.out.println("============================================================================");
                    System.out.println("USER PANEL [CASE SENSITIVE]");
                    System.out.println("1. Login");
                    System.out.println("2. Registration");
                    System.out.println("0. Back");
                    System.out.print("Enter Option: ");
                    String option = input.nextLine();
                    System.out.println("============================================================================");
                    switch (option) {
                        case "1" -> {
                            User profile = new User();
                            profile.login();
                        }
                        case "2" -> {
                            Customer profile = new Customer();
                            profile.registration();
                        }
                        case "0" -> {
                            System.out.println("HEADING TO HOME");
                            System.out.println("============================================================================");
                        }
                        default -> {
                            System.out.println("============================================================================");
                            System.out.println("Wrong Option Selected! Please try again.");
                            System.out.println("============================================================================");
                        }
                    }
                }
                case "0" -> {
                    test = false;
                    System.out.println("============================================================================");
                    System.out.println("Exit");
                    System.out.println("============================================================================");
                }
                default -> {
                    System.out.println("============================================================================");
                    System.out.println("Wrong Option Selected! Please try again.");
                    System.out.println("============================================================================");
                }
            }
        }
    }
}
