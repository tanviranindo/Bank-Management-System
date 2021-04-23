import java.io.FileNotFoundException;

public class Account extends Bank {
    int number;
    float balance;

    protected void login() throws FileNotFoundException {
        String address = "C:\\Users\\Tanvir\\IdeaProjects\\Bank Management System\\src\\customer.txt";
        super.login(address, "User");
    }

    private int getNumber() {
        return number;
    }

    private void setNumber(int number) {
        this.number = number;
    }

    private float getBalance() {
        return balance;
    }

    private void setBalance(float balance) {
        this.balance = balance;
    }

    private void deposit() {
    }

    private void withdraw() {
    }
}
