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

    protected void editCustomerDetails() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(customerData));
        input = new Scanner(new File(customerData));
        input2 = new Scanner(System.in);
        System.out.print("Provide Customer ID: ");
        int c_id = input2.nextInt();
        StringBuilder newText = new StringBuilder();
        while (input.hasNextLine()) {
            String oldData = input.nextLine();
            String[] c_data = oldData.split(",");
            if (Integer.parseInt(c_data[0]) == c_id) {
                System.out.println("1. Name");
                System.out.println("2. E-Mail");
                System.out.println("3. Password");
                System.out.println("4. DOB");
                System.out.println("5. Contact");
                System.out.println("6. Address");
                System.out.print("Update: ");
                int search = input2.nextInt();
                input2 = new Scanner(System.in);
                System.out.print("Set Value: ");
                String value = input2.nextLine();

                writer.write(c_id + ",");
                for (int i = 1; i < c_data.length; i++) {
                    if (i == search) {
                        writer.write(value);
                    } else {
                        writer.write(c_data[i]);
                    }
                    if (i != c_data.length - 1) writer.write(",");
                }
            } else {
                writer.write(oldData);
            }
            writer.write("\r\n");
        }
        input.close();
        writer.close();
    }

//    public String replace(String[] arr, int index, String newValue) {
//        StringBuilder hello = new StringBuilder("1,");
//        for (int i = 1; i < arr.length; i++) {
//            if (i == index) {
//                hello.append(newValue);
//            } else {
//                hello.append(arr[i]);
//            }
//            if (i != arr.length - 1) hello.append(",");
//        }
//        System.out.println(hello.toString());
//        return hello.toString();
//    }

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
