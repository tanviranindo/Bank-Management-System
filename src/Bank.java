import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Bank {
    public String id;
    protected boolean authentication = false;

    protected boolean isAuthentication() {
        return authentication;
    }

    public void setAuthentication(boolean authentication) {
        this.authentication = authentication;
    }

    protected void login(String address, String type) {
        try {
            Scanner data = new Scanner(new File(address));
            Scanner input = new Scanner(System.in);
            System.out.print("Enter " + type + "_ID: ");
            id = input.nextLine();
            System.out.print("Enter Password: ");
            String password = input.nextLine();
            while (data.hasNextLine()) {
                String[] account = data.nextLine().split(",");
//            if (account[0].equals(id) || account[1].equals(password)) {
//                if (!account[0].equals(id)) {
//                    System.out.print("Account doesn't exist. ");
//                } else if (!account[1].equals(password)) {
//                    System.out.print("Wrong password. ");
//                } else {
//                    setAuthentication(true);
//                }
//            }
                if (account[0].equals(id) && account[1].equals(password)) setAuthentication(true);
            }
            data.close();
            System.out.println("==================================");
            System.out.println(isAuthentication() ? "Authentication Successful" : "Authentication Failed");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
