import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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


        Scanner data = new Scanner(new File(customerData));
    }

    protected void verification() {
        System.out.println("Write : name + account_number + balance");
    }

    protected void showCustomerDetails() {
//        Scanner data = new Scanner(new File(customerData));
        //Setting attributes
        List<String> attributes = new ArrayList<>();
        attributes.add("Name");
        attributes.add("Contact");
        attributes.add("Address");
        attributes.add("DOB");
        attributes.add("Password");

        //Setting values
        List<List<String>> rowsList = new ArrayList<>();

        TableGenerator table = new TableGenerator();
        System.out.print(table.generateTable(attributes, rowsList));
    }

    protected void showAccountDetails() {

    }
}
