import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Manager extends Bank {
    List<List<String>> account;
    List<List<String>> customer;
    String accountData = "C:\\Users\\Tanvir\\IdeaProjects\\Bank Management System\\src\\account.txt";
    String customerData = "C:\\Users\\Tanvir\\IdeaProjects\\Bank Management System\\src\\customer.txt";
    String adminData = "C:\\Users\\Tanvir\\IdeaProjects\\Bank Management System\\src\\admin.txt";

    private Scanner input, input2;

    protected void login() throws IOException {
        super.login(adminData, "Admin");
        if (isAuthentication()) showOptions();
    }

    private void showOptions() throws IOException {
        System.out.println("==================================");
        input = new Scanner(System.in);
        System.out.println("1. Show Account Details");
        System.out.println("2. Show Customer Details");
        System.out.println("3. Edit Customer Details");
        System.out.println("4. Sign Out");
        System.out.print("Select to Operate: ");
        int option = input.nextInt();
        if (option == 2) {
            showCustomerDetails();
        } else if (option == 3) {
            editCustomerDetails();
        } else if (option == 4) {
            super.setAuthentication(false);
            System.out.println("==================================");
            System.out.println("Logged Out");
            System.out.println("==================================");
        }
    }

    protected void verification() {
        //CODE
        System.out.println("Write : name + account_number + balance");
    }

    protected void showCustomerDetails() throws FileNotFoundException {
        input = new Scanner(new File(customerData));
        String tableName = "CUSTOMER DATA: ";

        //Setting attributes
        List<String> attributes = new ArrayList<>();
        attributes.add("C_ID");
        attributes.add("Name");
        attributes.add("E-Mail");
        attributes.add("Password");
        attributes.add("DOB");
        attributes.add("Contact");
        attributes.add("Address");

        //Setting values
        settingRowValues(input, tableName, attributes);
    }

    protected void editCustomerDetails() {
        File fileToBeModified = new File(customerData);
        StringBuilder oldContent = new StringBuilder();
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(fileToBeModified));
            String line = reader.readLine();
            while (line != null) {
                oldContent.append(line).append(System.lineSeparator());
                line = reader.readLine();
            }

            String[] eachLine = oldContent.toString().split("\r\n");
            StringBuilder newContent = new StringBuilder();

            System.out.print("Provide Customer ID: ");
            int c_id = input.nextInt();
            System.out.println("1. Name");
            System.out.println("2. E-Mail");
            System.out.println("3. Password");
            System.out.println("4. DOB");
            System.out.println("5. Contact");
            System.out.println("6. Address");
            System.out.print("Update: ");
            int change = input.nextInt();
            System.out.print("Set Value: ");
            input = new Scanner(System.in);
            String value = input.nextLine();

            for (String i : eachLine) {
                String[] eachValue = i.split(",");
                for (int j = 0; j < eachValue.length; j++) {
                    if (Integer.parseInt(eachValue[0]) == c_id) {
                        eachValue[change] = value;
                    }
                    newContent.append(eachValue[j]);
                    if (j != eachValue.length - 1) newContent.append(",");
                }
                newContent.append("\r\n");
            }

            FileWriter file = new FileWriter(fileToBeModified);
            file.write(newContent.toString());
            reader.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void showAccountDetails() throws FileNotFoundException {
        input = new Scanner(new File(accountData));
        String tableName = "ACCOUNT DATA: ";
        //Setting attributes
        List<String> attributes = new ArrayList<>();
        attributes.add("C_ID");
        attributes.add("Account Number");
        attributes.add("Account Type");
        attributes.add("Card Number");
        attributes.add("Balance");

        //Setting values
        settingRowValues(input, tableName, attributes);
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
