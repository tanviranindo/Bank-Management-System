import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Bank {
    protected boolean authentication = false;

    protected boolean isAuthentication() {
        return authentication;
    }

    public void setAuthentication(boolean authentication) {
        this.authentication = authentication;
    }

    protected boolean login(String address) throws FileNotFoundException {
        Scanner data = new Scanner(new File(address));
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Admin_ID: ");
        String id = input.nextLine();
        System.out.print("Enter Password: ");
        String password = input.nextLine();
        while (data.hasNextLine()) {
            String[] account = data.nextLine().split(" ");
            if (account[0].equals(id) || account[1].equals(password)) {
                if (!account[0].equals(id)) {
                    System.out.print("Account doesn't exist. ");
                } else if (!account[1].equals(password)) {
                    System.out.print("Wrong password. ");
                } else {
                    setAuthentication(true);
                }
            }
        }
        data.close();
        System.out.println(isAuthentication() ? "Authentication successful." : "Authentication failed!");

        return isAuthentication();
    }
}
