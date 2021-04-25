import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Manager extends Bank {
    String accountData = "C:\\Users\\Tanvir\\IdeaProjects\\Bank Management System\\src\\account.txt";
    String customerData = "C:\\Users\\Tanvir\\IdeaProjects\\Bank Management System\\src\\customer.txt";
    String adminData = "C:\\Users\\Tanvir\\IdeaProjects\\Bank Management System\\src\\admin.txt";
    String transactionData = "C:\\Users\\Tanvir\\IdeaProjects\\Bank Management System\\src\\transaction.txt";

    protected void login() {
        super.login(adminData, "ADMIN");
        if (isAuthentication()) this.showOptions();
    }

    private void showOptions() {
        while (isAuthentication()) {
            Scanner input = new Scanner(System.in);

            System.out.println("1. Verify Customer");
            System.out.println("2. Show Accounts Details");
            System.out.println("3. Update Account Details");
            System.out.println("4. Delete Account Details");
            System.out.println("5. Show Customer Details");
            System.out.println("6. Update Customer Details");
            System.out.println("7. Delete Customer Details");
            System.out.println("8. Show All Transactions");
            System.out.println("0. Sign Out");
            System.out.print("Select Operation: ");
            int option = input.nextInt();

            if (option == 1) {
                verifyCustomer();
            } else if (option == 2) {
                showAccountDetails();
            } else if (option == 3) {
                showAccountDetails();
                updateAccountDetails();
            } else if (option == 4) {
                showAccountDetails();
                deleteAccount();
            } else if (option == 5) {
                showCustomerDetails();
            } else if (option == 6) {
                showCustomerDetails();
                updateCustomerDetails();
            } else if (option == 7) {
                showCustomerDetails();
                deleteCustomer();
            } else if (option == 8) {
                showAllTransaction();
            }
//            else if (option == 9) {
//                showAllTransaction();//Delete Transaction
//            }
            else if (option == 0) {
                super.setAuthentication(false);
                System.out.println("============================================================");
                System.out.println("Logged Out");
                System.out.println("============================================================");
            } else {
                System.out.println("============================================================");
                System.out.println("Wrong Option Selected! Please try again.");
                System.out.println("============================================================");
            }
        }
    }

    protected void verifyCustomer() {
        //CODE
        showCustomerDetails();
        Account verify = new Account();
        System.out.print("Customer ID to Verify: ");
        Scanner input = new Scanner(System.in);
        String c_id = input.nextLine();
        verify.createAccount(c_id);
    }

    protected void showCustomerDetails() {
        //Setting attributes
        List<String> attributes = new ArrayList<>();
        attributes.add("C_ID");
        attributes.add("NAME");
        attributes.add("E-MAIL");
        attributes.add("PASSWORD");
        attributes.add("BALANCE (\u09F3)");
        attributes.add("DOB");
        attributes.add("CONTACT");
        attributes.add("ADDRESS");

        //Setting values
        try {
            Scanner input = new Scanner(new File(customerData));
            String tableName = "CUSTOMER DATA: ";
            settingRowValues(input, tableName, attributes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void showAccountDetails() {
        //Setting attributes
        List<String> attributes = new ArrayList<>();
        attributes.add("C_ID");
        attributes.add("PASSWORD");
        attributes.add("NAME");
        attributes.add("ACCOUNT NUMBER");
        attributes.add("BALANCE (\u09F3)");

        //Setting values
        try {
            Scanner input = new Scanner(new File(accountData));
            String tableName = "ACCOUNT DATA: ";
            settingRowValues(input, tableName, attributes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void updateAccountDetails() {
        File fileToBeModified = new File(accountData);
        try {
            String[] eachLine = readFile(accountData);
            StringBuilder newContent = new StringBuilder();
            Scanner input = new Scanner(System.in);
            System.out.print("Provide Customer ID: ");
            String c_id = input.nextLine();
            System.out.println("1. Password");
            System.out.println("2. Name");
            System.out.println("3. Account");
            System.out.println("4. Balance");
            System.out.println("5. Back");
            System.out.print("Update: ");
            int change = input.nextInt();

            if (change == 5) return;

            modifyDetails(fileToBeModified, eachLine, newContent, c_id, change);
        } catch (IOException e) {
            System.out.println("============================================================");
            System.out.println("Status: Could not be processed due to an error.");
            System.out.println("============================================================");
        }
    }

    protected void updateCustomerDetails() {
        File fileToBeModified = new File(customerData);
        try {
            String[] eachLine = readFile(customerData);
            StringBuilder newContent = new StringBuilder();
            Scanner input = new Scanner(System.in);
            System.out.print("Provide Customer ID: ");
            String c_id = input.nextLine();
            System.out.println("1. Name");
            System.out.println("2. E-Mail");
            System.out.println("3. Password");
            System.out.println("4. Balance");
            System.out.println("5. DOB");
            System.out.println("6. Contact");
            System.out.println("7. Address");
            System.out.print("Update: ");
            int change = input.nextInt();
            modifyDetails(fileToBeModified, eachLine, newContent, c_id, change);
        } catch (IOException e) {
            System.out.println("============================================================");
            System.out.println("Status: Could not be processed due to an error.");
            System.out.println("============================================================");
        }
    }

    private void modifyDetails(File fileToBeModified, String[] eachLine, StringBuilder newContent, String c_id, int change) {
        Scanner input;
        System.out.print("Set Value: ");
        input = new Scanner(System.in);
        String value = input.nextLine();

        for (String i : eachLine) {
            String[] eachValue = i.split(",");
            for (int j = 0; j < eachValue.length; j++) {
                if (eachValue[0].equals(c_id)) {
                    eachValue[change] = value;
                }
                newContent.append(j != 3 ? eachValue[j].toUpperCase() : eachValue[j]);
                if (j != eachValue.length - 1) newContent.append(",");
            }
            newContent.append("\r\n");
        }

        try {
            OutputStream outputStream = new FileOutputStream(fileToBeModified);
            Writer file = new OutputStreamWriter(outputStream);
            file.write(newContent.toString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("============================================================");
        System.out.println("Status: C_ID(" + c_id + ") has been updated.");
        System.out.println("============================================================");
    }

    protected void deleteAccount() {
        deleteRow(accountData);
    }

    protected void deleteCustomer() {
        deleteRow(customerData);
    }

    private void deleteRow(String accountData) {
        File fileToBeModified = new File(accountData);
        try {
            String[] eachLine = readFile(accountData);
            StringBuilder newContent = new StringBuilder();

            Scanner input = new Scanner(System.in);
            System.out.print("Provide Customer ID: ");
            String c_id = input.nextLine();

            for (String i : eachLine) {
                String[] eachValue = i.split(",");
                if (!eachValue[0].equals(c_id)) newContent.append(i).append("\r\n");
            }

            OutputStream outputStream = new FileOutputStream(fileToBeModified);
            Writer file = new OutputStreamWriter(outputStream);
            file.write(newContent.toString());
            file.close();
            System.out.println("============================================================");
            System.out.println("Status: C_ID(" + c_id + ") has been deleted.");
            System.out.println("============================================================");
        } catch (IOException e) {
            System.out.println("============================================================");
            System.out.println("Status: Could not be processed due to an error.");
            System.out.println("============================================================");
        }
    }

    protected void showAllTransaction() {
        //Setting attributes
        List<String> attributes = new ArrayList<>();
        attributes.add("C_ID");
        attributes.add("NAME");
        attributes.add("ACCOUNT NUMBER");
        attributes.add("TRX_ID");
        attributes.add("TRANSACTION TYPE");
        attributes.add("AMOUNT (\u09F3)");
        attributes.add("BALANCE (\u09F3)");
        attributes.add("STATUS (\u09F3)");

        //Setting values
        try {
            Scanner input = new Scanner(new File(transactionData));
            String tableName = "TRANSACTION DETAILS: ";
            settingRowValues(input, tableName, attributes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    protected String[] readFile(String fileName) throws FileNotFoundException {
        Scanner input = new Scanner(new File(fileName));
        StringBuilder list = new StringBuilder();
        while (input.hasNextLine()) {
            list.append(input.nextLine()).append(System.lineSeparator());
        }
        input.close();
        return list.toString().split("\r\n");
    }

    private void settingRowValues(Scanner input, String tableName, List<String> attributes) {
        List<List<String>> rowsList = new ArrayList<>();
        while (input.hasNextLine()) {
            rowsList.add(Arrays.asList(input.nextLine().split(",")));
        }
        input.close();
        TableGenerator table = new TableGenerator();
        System.out.print(table.generateTable(attributes, tableName, rowsList));
    }
}
