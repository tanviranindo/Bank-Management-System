import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Manager extends Bank {
    String accountData = "account.txt";
    String customerData = "customer.txt";
    String adminData = "admin.txt";
    String transactionData = "transaction.txt";

    //Created under a super class name "BANK" (method overriding)
    protected void login() {
        super.login(adminData, "ADMIN");
        if (isAuthentication()) this.showOptions();
    }

    //Manager Panel
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
            System.out.println("9. Delete Transactions Details");
            System.out.println("0. Sign Out");
            System.out.print("Select Operation: ");
            String option = input.nextLine();

            switch (option) {
                case "1" -> verifyCustomer();
                case "2" -> showAccountDetails();
                case "3" -> {
                    showAccountDetails();
                    updateAccountDetails();
                }
                case "4" -> {
                    showAccountDetails();
                    deleteAccount();
                }
                case "5" -> showCustomerDetails();
                case "6" -> {
                    showCustomerDetails();
                    updateCustomerDetails();
                }
                case "7" -> {
                    showCustomerDetails();
                    deleteCustomer();
                }
                case "8" -> showAllTransaction();
                case "9" -> {
                    showAllTransaction();
                    deleteTransaction();
                }
                case "0" -> {
                    super.setAuthentication(false);
                    System.out.println("============================================================================");
                    System.out.println("Logged Out");
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

    //Reading customer.txt file and creating account
    protected void verifyCustomer() {
        //CODE
        showCustomerDetails(); //View Customer Data
        Account verify = new Account();
        System.out.print("Customer ID to Verify: ");
        Scanner input = new Scanner(System.in);
        String c_id = input.nextLine(); //Particular ID to verify
        verify.createAccount(c_id);
    }

    protected void showCustomerDetails() {
        //Setting attributes
        List<String> attributes = new ArrayList<>();
        attributes.add("C_ID");
        attributes.add("NAME");
        attributes.add("E-MAIL");
        attributes.add("PASSWORD");
        attributes.add("BALANCE (BDT)");
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
        attributes.add("BALANCE (BDT)");

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
            System.out.println("0. Back");
            System.out.print("Update: ");
            String change = input.nextLine();

            boolean flag = false;
            if (change.equals("0")) return;
            else if (change.equals("1") || change.equals("2") || change.equals("3") || change.equals("4")) flag = true;

            if (flag) {
                modifyDetails(fileToBeModified, eachLine, newContent, c_id, change);
            } else {
                System.out.println("============================================================================");
                System.out.println("Wrong Option Selected! Please try again.");
                System.out.println("============================================================================");
            }
        } catch (IOException e) {
            System.out.println("============================================================================");
            System.out.println("Status: Could not be processed due to an error.");
            System.out.println("============================================================================");
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
            System.out.println("0. Back");
            System.out.print("Update: ");
            String change = input.nextLine();

            boolean flag = false;
            if (change.equals("0")) return;
            else if (change.equals("1") || change.equals("2") || change.equals("3") || change.equals("4") || change.equals("5") || change.equals("6") || change.equals("7"))
                flag = true;

            if (flag) {
                modifyDetails(fileToBeModified, eachLine, newContent, c_id, change);
            } else {
                System.out.println("============================================================================");
                System.out.println("Wrong Option Selected! Please try again.");
                System.out.println("============================================================================");
            }
        } catch (IOException e) {
            System.out.println("============================================================================");
            System.out.println("Status: Could not be processed due to an error.");
            System.out.println("============================================================================");
        }
    }


    private void modifyDetails(File fileToBeModified, String[] eachLine, StringBuilder newContent, String c_id, String change) {
        System.out.print("Set Value: ");
        Scanner input = new Scanner(System.in);
        String value = input.nextLine();
        boolean flag = false;

        for (String i : eachLine) {
            String[] eachValue = i.split(",");
            for (int j = 0; j < eachValue.length; j++) {
                if (eachValue[0].equals(c_id)) {
                    eachValue[Integer.parseInt(change)] = value;
                    if (change.equals("4") && !flag) {
                        Account one = new Account();
                        one.init(c_id);
                        flag = true;
                        one.makeTransaction("MANAGEMENT", one.getBalance(), true);
                    }
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

        System.out.println("============================================================================");
        System.out.println("Status: C_ID(" + c_id + ") has been updated.");
        System.out.println("============================================================================");
    }

    protected void deleteAccount() {
        deleteRow(accountData);
    }

    protected void deleteCustomer() {
        deleteRow(customerData);
    }

    protected void deleteTransaction() {
        File fileToBeModified = new File(transactionData);
        try {
            String[] eachLine = readFile(transactionData);
            StringBuilder newContent = new StringBuilder();

            Scanner input = new Scanner(System.in);
            System.out.print("Provide Transaction ID: ");
            String transactionId = input.nextLine().toUpperCase();
            boolean flag = false;

            for (String i : eachLine) {
                String[] eachValue = i.split(",");
                if (!eachValue[3].equals(transactionId)) {
                    newContent.append(i).append("\r\n");
                } else flag = true;
            }

            try {
                OutputStream outputStream = new FileOutputStream(fileToBeModified);
                Writer file = new OutputStreamWriter(outputStream);
                file.write(newContent.toString());
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (flag) {
                System.out.println("============================================================================");
                System.out.println("Status: " + transactionId + " has been deleted.");
                System.out.println("============================================================================");
            } else {
                System.out.println("============================================================================");
                System.out.println("Status: " + transactionId + " not found.");
                System.out.println("============================================================================");
            }
        } catch (IOException e) {
            System.out.println("============================================================================");
            System.out.println("Status: Could not be processed due to an error.");
            System.out.println("============================================================================");
        }
    }

    //Delete Account
    private void deleteRow(String accountData) {
        File fileToBeModified = new File(accountData);
        try {
            String[] eachLine = readFile(accountData);
            StringBuilder newContent = new StringBuilder();

            Scanner input = new Scanner(System.in);
            System.out.print("Provide Customer ID: ");
            String c_id = input.nextLine().toUpperCase();
            boolean flag = false;

            for (String i : eachLine) {
                String[] eachValue = i.split(",");
                if (!eachValue[0].equals(c_id)) {
                    newContent.append(i).append("\r\n");
                } else flag = true;
            }

            try {
                OutputStream outputStream = new FileOutputStream(fileToBeModified);
                Writer file = new OutputStreamWriter(outputStream);
                file.write(newContent.toString());
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (flag) {
                System.out.println("============================================================================");
                System.out.println("Status: C_ID(" + c_id + ") has been deleted.");
                System.out.println("============================================================================");
            } else {
                System.out.println("============================================================================");
                System.out.println("Status: C_ID(" + c_id + ") not found.");
                System.out.println("============================================================================");
            }
        } catch (IOException e) {
            System.out.println("============================================================================");
            System.out.println("Status: Could not be processed due to an error.");
            System.out.println("============================================================================");
        }
    }

    protected void showAllTransaction() {
        //Setting attributes
        List<String> attributes = transactionAttributes();

        //Setting values
        try {
            Scanner input = new Scanner(new File(transactionData));
            String tableName = "TRANSACTION DETAILS: ";
            settingRowValues(input, tableName, attributes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected List<String> transactionAttributes() {
        List<String> attributes = new ArrayList<>();
        attributes.add("C_ID");
        attributes.add("NAME");
        attributes.add("ACCOUNT NUMBER");
        attributes.add("TRX_ID");
        attributes.add("TRANSACTION TYPE");
        attributes.add("AMOUNT (BDT)");
        attributes.add("BALANCE (BDT)");
        attributes.add("STATUS");
        return attributes;
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