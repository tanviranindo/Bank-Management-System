import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Account {
    int customerID;
    String name;
    String account;
    String password;
    float balance;

    String accountData = "C:\\Users\\Tanvir\\IdeaProjects\\Bank Management System\\src\\account.txt";
    String customerData = "C:\\Users\\Tanvir\\IdeaProjects\\Bank Management System\\src\\customer.txt";

    public int getCustomerID() {
        return customerID;
    }

    private void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    private void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    public float getBalance() {
        return balance;
    }

    protected void setBalance(float balance) {
        this.balance = balance;
    }

    protected void deposit() {
    }

    protected void withdraw() {
    }

    protected void createAccount(int c_id) throws FileNotFoundException {
        String[] eachLine = readFile(customerData);
        for (String i : eachLine) {
            String[] eachValue = i.split(",");
            if (Integer.parseInt(eachValue[0]) == c_id) {
                setCustomerID(Integer.parseInt(eachValue[0]));
                setName(eachValue[1].toUpperCase());
                setPassword(eachValue[3]);
                generateAccount();
                setBalance(Float.parseFloat(eachValue[4]));
            }
        }

        //Previous existence
        eachLine = readFile(accountData);
        boolean flag = true;
        for (String i : eachLine) {
            String[] eachValue = i.split(",");
            if (Integer.parseInt(eachValue[0]) == c_id) {
                flag = false;
            }
        }
        if (flag) dataEntry();
        else System.out.println("Status: C_ID " + getCustomerID() + " has been already verified.");
    }


    //Delete an account

    private void dataEntry() {
        try {
            FileWriter file = new FileWriter(accountData, true);
            file.write(getCustomerID() + "," + getName() + "," + getPassword() + "," + getAccount() + "," + getBalance() + "\n");
            file.close();
            System.out.println("Status: " + getName() + "(CID-" + getCustomerID() + ") has been verified.");
        } catch (IOException ignored) {
            System.out.println("Status: Verification could not be processed due to an error.");
        }
    }


    private void generateAccount() {
        Random rand = new Random();
        StringBuilder card = new StringBuilder();
        StringBuilder accountNumber = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            int n = rand.nextInt(10);
            card.append(n);
        }
        for (int i = 0; i < 16; i++) {
            if (i % 4 == 0 && i != 0) accountNumber.append("-");
            accountNumber.append(card.charAt(i));
        }
        setAccount(accountNumber.toString());
    }

    private String[] readFile(String fileName) throws FileNotFoundException {
        Scanner input = new Scanner(new File(fileName));
        StringBuilder list = new StringBuilder();
        while (input.hasNextLine()) {
            list.append(input.nextLine()).append(System.lineSeparator());
        }
        input.close();
        return list.toString().split("\r\n");
    }
}
