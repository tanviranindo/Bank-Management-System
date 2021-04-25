import java.util.Scanner;

public class User extends Account {
    /**
     * View Balance
     * Withdraw (Accounts CLASS)
     * Deposit (Accounts CLASS)
     * View Details
     * Transaction History
     * Logout
     **/
    protected void login() {
        super.login(accountData, "User");
        if (isAuthentication()) this.showOptions();
    }

    private void showOptions() {
        Scanner input = new Scanner(System.in);
        System.out.println("==================================");
        System.out.println("1. View Balance");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. View Details");
        System.out.println("5. Transaction History");
        System.out.println("6. Sign Out");
        System.out.print("Select Operation: ");
        int option = input.nextInt();
        if (option == 1) {
            getBalance();
//            verifyCustomer();
        } else if (option == 2) {
//            showAccountDetails();
        } else if (option == 3) {
//            deleteAccount();
        } else if (option == 4) {
//            showCustomerDetails();
        } else if (option == 5) {
//            showCustomerDetails();
//            updateCustomerDetails();
        } else if (option == 6) {
//            deleteCustomer();
        } else if (option == 7) {
            super.setAuthentication(false);
            System.out.println("==================================");
            System.out.println("Logged Out");
            System.out.println("==================================");
        }
    }
}
