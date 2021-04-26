import java.util.Scanner;

public class User extends Account {
    protected void login() {
        super.login(accountData, "USER");
        if (isAuthentication()) {
            init(super.id);
            showOptions();
        }
    }

    private void showOptions() {
        while (isAuthentication()) {
            Scanner input = new Scanner(System.in);
            System.out.println("1. View Balance");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. View Details");
            System.out.println("5. Transaction History");
            System.out.println("0. Sign Out");
            System.out.print("Select Operation: ");
            int option = input.nextInt();


            if (option == 1) {
                System.out.println("============================================================================");
                System.out.println("Your Balance is BDT " + super.getBalance() + "/-");
                System.out.println("============================================================================");
            } else if (option == 2) {
                System.out.print("Withdraw Amount: ");
                float amount = input.nextFloat();
                System.out.println("============================================================================");
                withdraw(amount);
                System.out.println("============================================================================");
            } else if (option == 3) {
                System.out.print("Deposit Amount: ");
                float amount = input.nextFloat();
                System.out.println("============================================================================");
                deposit(amount);
                System.out.println("============================================================================");
            } else if (option == 4) {
                viewDetails();
            } else if (option == 5) {
                showTransaction();
            } else if (option == 0) {
                super.setAuthentication(false);
                System.out.println("============================================================================");
                System.out.println("Logged Out");
                System.out.println("============================================================================");
            } else {
                System.out.println("============================================================================");
                System.out.println("Wrong Option Selected! Please try again.");
                System.out.println("============================================================================");
            }
        }
    }
}
