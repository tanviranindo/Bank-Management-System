import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Bank {
    public String id;
    public String name;
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
                if (account[0].equals(id) && account[1].equals(password)) {
                    setAuthentication(true);
                    if (type.equals("USER")) this.name = account[2];
                }
            }
            data.close();

            System.out.println("============================================================================");
            if (isAuthentication()) {
                if (type.equals("USER")) {
                    System.out.println("HELLO " + this.name + "!");
                } else System.out.println("Authentication Successful");
            } else {
                System.out.println("Authentication Failed");
            }
            System.out.println("============================================================================");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}