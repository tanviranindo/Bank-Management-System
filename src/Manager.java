import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Manager extends Bank {
    String accountData = "C:\\Users\\Tanvir\\IdeaProjects\\Bank Management System\\src\\account.txt";
    String customerData = "C:\\Users\\Tanvir\\IdeaProjects\\Bank Management System\\src\\customer.txt";
    String adminData = "C:\\Users\\Tanvir\\IdeaProjects\\Bank Management System\\src\\admin.txt";

    protected void login() {
        super.login(adminData, "ADMIN");
        if (isAuthentication()) this.showOptions();
    }

    private void showOptions() {
        Scanner input = new Scanner(System.in);
        System.out.println("1. Verify Customer");
        System.out.println("2. Show Accounts Details");
        System.out.println("3. Delete Account Details");
        System.out.println("4. Show Customer Details");
        System.out.println("5. Update Customer Details");
        System.out.println("6. Delete Customer Details");
        System.out.println("7. Sign Out");
        System.out.print("Select Operation: ");
        int option = input.nextInt();
        if (option == 1) {
            verifyCustomer();
        } else if (option == 2) {
            showAccountDetails();
        } else if (option == 3) {
            showAccountDetails();
            deleteAccount();
        } else if (option == 4) {
            showCustomerDetails();
        } else if (option == 5) {
            showCustomerDetails();
            updateCustomerDetails();
        } else if (option == 6) {
            showCustomerDetails();
            deleteCustomer();
        } else if (option == 7) {
            super.setAuthentication(false);
            System.out.println("==================================");
            System.out.println("Logged Out");
            System.out.println("==================================");
        }
    }

    protected void verifyCustomer() {
        //CODE
        showCustomerDetails();
        Account verify = new Account();
        System.out.print("Customer ID to Verify: ");
        Scanner input = new Scanner(System.in);
        String c_id = input.nextLine();

        try {
            verify.createAccount(c_id);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void showCustomerDetails() {
        //Setting attributes
        List<String> attributes = new ArrayList<>();
        attributes.add("C_ID");
        attributes.add("Name");
        attributes.add("E-Mail");
        attributes.add("Password");
        attributes.add("Balance (\u09F3)");
        attributes.add("DOB");
        attributes.add("Contact");
        attributes.add("Address");

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
        attributes.add("Password");
        attributes.add("Name");
        attributes.add("Account Number");
        attributes.add("Balance (\u09F3)");

        //Setting values
        try {
            Scanner input = new Scanner(new File(accountData));
            String tableName = "ACCOUNT DATA: ";
            settingRowValues(input, tableName, attributes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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

            FileWriter file = new FileWriter(fileToBeModified);
            file.write(newContent.toString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

            FileWriter file = new FileWriter(fileToBeModified);
            file.write(newContent.toString());
            file.close();
        } catch (IOException e) {
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
