import java.io.File;
import java.io.FileNotFoundException;
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

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    private void deposit() {
    }

    private void withdraw() {
    }

    protected void createAccount(int c_id) throws FileNotFoundException {
        Scanner input = new Scanner(new File(customerData));
        StringBuilder list = new StringBuilder();
        while (input.hasNextLine()) {
            list.append(input.nextLine()).append(System.lineSeparator());
        }
        input.close();

        String[] eachLine = list.toString().split("\r\n");
        for (int i = 0; i < eachLine.length; i++) {
            String[] eachValue = eachLine[i].split(",");
            if (Integer.parseInt(eachValue[0]) == c_id) {
                setCustomerID(Integer.parseInt(eachValue[0]));
                setName(eachValue[1].toUpperCase());
                generateAccount();
                setBalance(Float.parseFloat(eachValue[4]));
            }
        }
    }


    private String generateAccount() {
        try {
            Scanner input = new Scanner(new File(accountData));
            StringBuilder list = new StringBuilder();
            while (input.hasNextLine()) {
                list.append(input.nextLine()).append(System.lineSeparator());
            }
            input.close();

            String account = "4000-0000-0000-0000";
            String[] eachLine = list.toString().split("\r\n");
            String lastGeneratedAccount = eachLine[eachLine.length - 1].split(",")[2];
            String[] arr = lastGeneratedAccount.split("-");

            System.out.println(account);
            return lastGeneratedAccount;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return getAccount();
    }
}
