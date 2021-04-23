import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Bank {
    protected String user;
    protected String password;
    protected boolean authentication = false;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    protected boolean isAuthentication() {
        return authentication;
    }

    public void setAuthentication(boolean authentication) {
        this.authentication = authentication;
    }

    protected void login() throws FileNotFoundException {
        String address = "C:\\Users\\Tanvir\\IdeaProjects\\ATM Booth\\src\\Test\\admin";
        Scanner data = new Scanner(new File(address));
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Admin_ID: ");
        String adminID = input.nextLine();
        System.out.print("Enter Password: ");
        String password = input.nextLine();
        while (data.hasNextLine()) {
            String[] account = data.nextLine().split(" ");
            if (account[0].equals(adminID) || account[1].equals(password)) {
                if (!account[0].equals(user)) {
                    System.out.print("Account doesn't exist. ");
                } else if (!account[1].equals(getUser())) {
                    System.out.print("Wrong password. ");
                } else {
                    setAuthentication(true);
                }
            }
        }
        data.close();
        System.out.println(isAuthentication() ? "Authentication successful." : "Authentication failed!");
    }
}
