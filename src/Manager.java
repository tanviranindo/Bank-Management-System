import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Manager extends Bank {
    String accountData = "C:\\Users\\Tanvir\\IdeaProjects\\Bank Management System\\src\\account.txt";
    String customerData = "C:\\Users\\Tanvir\\IdeaProjects\\Bank Management System\\src\\customer.txt";
    String adminData = "C:\\Users\\Tanvir\\IdeaProjects\\Bank Management System\\src\\admin.txt";

    protected void login() throws IOException {
        super.login(adminData, "Admin");
        if (isAuthentication()) showOptions();
    }

    private void showOptions() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        System.out.println("==================================");
        System.out.println("1. Show Account Details");
        System.out.println("2. Edit Account Details");
        System.out.println("3. Verify Account");
        System.out.println("4. Show Customer Details");
        System.out.println("5. Edit Customer Details");
        System.out.println("6. Sign Out");
        System.out.print("Select to Operate: ");
        int option = input.nextInt();
        if (option == 3) {
            verifyCustomer();
        } else if (option == 4) {
            showCustomerDetails();
        } else if (option == 5) {
            showCustomerDetails();
            editCustomerDetails();
        } else if (option == 6) {
            super.setAuthentication(false);
            System.out.println("==================================");
            System.out.println("Logged Out");
            System.out.println("==================================");
        }
    }

    protected void verifyCustomer() throws FileNotFoundException {
        //CODE
        System.out.println("Write : c_id + name + account_number + balance");
        showCustomerDetails();
        Account verify = new Account();
        verify.createAccount(1);
    }

    protected void showCustomerDetails() {
        //Setting attributes
        List<String> attributes = new ArrayList<>();
        attributes.add("C_ID");
        attributes.add("Name");
        attributes.add("E-Mail");
        attributes.add("Password");
        attributes.add("Balance");
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

            Scanner input = new Scanner(System.in);
            System.out.print("Provide Customer ID: ");
            int c_id = input.nextInt();
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
                    if (Integer.parseInt(eachValue[0]) == c_id) {
                        eachValue[change] = value;
                    }
                    newContent.append(j != 3 ? eachValue[j].toUpperCase() : eachValue[j]);
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

    protected void showAccountDetails() {
        //Setting attributes
        List<String> attributes = new ArrayList<>();
        attributes.add("C_ID");
        attributes.add("Name");
        attributes.add("Account Number");
        attributes.add("Balance");

        //Setting values
        try {
            Scanner input = new Scanner(new File(accountData));
            String tableName = "ACCOUNT DATA: ";
            settingRowValues(input, tableName, attributes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
