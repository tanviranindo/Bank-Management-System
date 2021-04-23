import java.io.FileNotFoundException;

public class Manager extends Bank {
    protected void login() throws FileNotFoundException {
        String address = "C:\\Users\\Tanvir\\IdeaProjects\\Bank Management System\\src\\admin.txt";
        super.login(address);
    }
}
