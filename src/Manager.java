import java.io.File;
import java.io.FileNotFoundException;
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

    protected void login() throws FileNotFoundException {
        super.login(adminData, "Admin");
        if (isAuthentication()) showOptions();
    }

    private void showOptions() throws FileNotFoundException {
        System.out.println("==================================");
        Scanner input = new Scanner(System.in);
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
        Scanner data = new Scanner(new File(customerData));
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
        settingRowValues(data, tableName, attributes);
    }

    protected void editCustomerDetails() throws FileNotFoundException {
//        try {
////            File file = new File(customerData);
//            BufferedReader reader = new BufferedReader(new FileReader(customerData));
//            String line = "CID", oldtext = "";
//            Scanner input = new Scanner(System.in);
//            System.out.print("Provide Customer ID: ");
//            int c_id = input.nextInt();
//            while ((line = reader.readLine()) != null) {
//                oldtext += line + "\r\n";
//            }
//            reader.close();
//            // replace a word in a file
//            //String newtext = oldtext.replaceAll("drink", "Love");
//
//            //To replace a line in a file
//            String newtext = oldtext.replaceAll("This is test string 20000", "blah blah blah");
//
//            FileWriter writer = new FileWriter("file.txt");
//            writer.write(newtext);
//            writer.close();
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }
//    }
//
        Scanner data = new Scanner(new File(customerData));
        Scanner input = new Scanner(System.in);
        System.out.print("Provide Customer ID: ");
        int c_id = input.nextInt();
        while (data.hasNextLine()) {
            String[] c_data = data.nextLine().split(",");
            if (Integer.parseInt(c_data[0]) == c_id) {
                System.out.println("1. Name");
                System.out.println("2. E-Mail");
                System.out.println("3. Password");
                System.out.println("4. DOB");
                System.out.println("5. Contact");
                System.out.println("6. Address");
                System.out.print("Update: ");
                int search = input.nextInt();

                StringBuilder oldText = new StringBuilder(c_data[search]);
                System.out.print("Set Value: ");
                String newText = input.nextLine();
                oldText.append(data.nextLine());

                System.out.println(oldText);
                System.out.println(newText);
                data.close();
            }
        }
    }

    protected void showAccountDetails() throws FileNotFoundException {
        Scanner data = new Scanner(new File(accountData));
        String tableName = "ACCOUNT DATA: ";
        //Setting attributes
        List<String> attributes = new ArrayList<>();
        attributes.add("C_ID");
        attributes.add("Account Number");
        attributes.add("Account Type");
        attributes.add("Card Number");
        attributes.add("Balance");

        //Setting values
        settingRowValues(data, tableName, attributes);
    }

    private void settingRowValues(Scanner data, String tableName, List<String> attributes) {
        List<List<String>> rowsList = new ArrayList<>();
        while (data.hasNextLine()) {
            rowsList.add(Arrays.asList(data.nextLine().split(",")));
        }
        data.close();
        TableGenerator table = new TableGenerator();
        System.out.print(table.generateTable(attributes, tableName, rowsList));
    }
}
