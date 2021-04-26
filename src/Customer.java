import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Customer extends Manager {
    int customerID = 0;
    String name;
    String email;
    String password;
    String startBalance;
    String DOB;
    String contact;
    String address;

    public void setID() {
        try {
            Scanner data = new Scanner(new File(super.customerData));
            int temp = 0;
            while (data.hasNextLine()) {
                String[] arr = data.nextLine().split(",");
                temp = Integer.parseInt(arr[0]);
            }
            this.customerID = temp + 1;
            data.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStartBalance(String startBalance) {
        this.startBalance = startBalance;
    }

    public String getStartBalance() {
        return startBalance;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    protected void registration() {
        Scanner input = new Scanner(System.in);
        setID();
        System.out.print("Enter Name: ");
        setName(input.nextLine().toUpperCase());
        System.out.print("Enter E-Mail: ");
        setEmail(input.nextLine().toUpperCase());
        System.out.print("Set Password: ");
        setPassword(input.nextLine());
        System.out.print("Starting Balance: ");
        setStartBalance(input.nextLine());
        System.out.print("Enter Date Of Birth (DD/MM/YEAR): ");
        setDOB(input.nextLine());
        System.out.print("Enter Contact Number: ");
        setContact(input.nextLine());
        System.out.print("Enter Address: ");
        setAddress(input.nextLine().toUpperCase());
        dataEntry();
    }

    private void dataEntry() {
        System.out.println("============================================================================");
        try {
            FileWriter file = new FileWriter(super.customerData, true);
            file.write(getCustomerID() + "," + getName() + "," + getEmail() + "," + getPassword() + ","
                    + getStartBalance() + "," + getDOB() + "," + getContact() + "," + getAddress() + "\n");
            file.close();
            System.out.println("Status: " + getName() + "(CID-" + getCustomerID() + ") has been created.");
        } catch (IOException ignored) {
            System.out.println("Status: Verification could not be processed due to an error.");
        }
        System.out.println("============================================================================");
    }
}
