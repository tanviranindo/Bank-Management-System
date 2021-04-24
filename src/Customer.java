import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Customer {
    int CID = 0;
    String name;
    String email;
    String password;
    String DOB;
    String contact;
    String address;

    public void setID() throws FileNotFoundException {
        String customerData = "C:\\Users\\Tanvir\\IdeaProjects\\Bank Management System\\src\\customer.txt";
        Scanner data = new Scanner(new File(customerData));
        int temp = 0;
        while (data.hasNextLine()) {
            String[] arr = data.nextLine().split(",");
            temp = Integer.parseInt(arr[0]);
        }
        this.CID = temp + 1;
        data.close();
    }

    public int getCID() {
        return CID;
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

    protected void registration() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        setID();
        System.out.print("Enter Name: ");
        setName(input.nextLine());
        System.out.print("Enter E-Mail: ");
        setEmail(input.nextLine());
        System.out.print("Set Password: ");
        setPassword(input.nextLine());
        System.out.print("Enter Date Of Birth (DD/MM/YEAR): ");
        setDOB(input.nextLine());
        System.out.print("Enter Contact Number: ");
        setContact(input.nextLine());
        System.out.print("Enter Address: ");
        setAddress(input.nextLine());
        dataEntry();
    }

    private void dataEntry() {
        try {
            String saveTo = "C:\\Users\\Tanvir\\IdeaProjects\\Bank Management System\\src\\customer.txt";
            FileWriter file = new FileWriter(saveTo, true);
            file.write(getCID() + "," + getName() + "," + getEmail() + "," + getPassword() + "," + getDOB() + "," + getContact() + "," + getAddress() + "\n");
            file.close();
        } catch (IOException ignored) {
            //Ignored
        }
    }
}
