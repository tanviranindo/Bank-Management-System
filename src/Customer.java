import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Customer {
    String CID;
    String name;
    String contact;
    String address;
    String DOB;
    String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    protected void registration() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Name: ");
        setName(input.nextLine());
        System.out.print("Enter Contact Number: ");
        setContact(input.nextLine());
        System.out.print("Enter Address: ");
        setAddress(input.nextLine());
        System.out.print("Enter Date Of Birth (DD/MM/YEAR): ");
        setDOB(input.nextLine());
        System.out.print("Set Password: ");
        setPassword(input.nextLine());
        dataEntry();
    }

    private void dataEntry() {
        try {
            String saveTo = "C:\\Users\\Tanvir\\IdeaProjects\\Bank Management System\\src\\customer.txt";
            FileWriter file = new FileWriter(saveTo);
            file.write(getName() + "," + getContact() + "," + getAddress() + "," + getDOB() + "," + getPassword());
            file.close();
        } catch (IOException ignored) {
            //Ignored
        }
    }
}
