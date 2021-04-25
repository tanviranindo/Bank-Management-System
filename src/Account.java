import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Account extends Manager {
    String transactionID;
    String customerID;
    String name;
    String account;
    String password;
    float balance;

    public String getTransactionID() {
        return transactionID;
    }

    private void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getCustomerID() {
        return customerID;
    }

    private void setCustomerID(String customerID) {
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

    protected void init(String id) {
        setCustomerID(id);
        try {
            String[] eachLine = super.readFile(super.accountData);
            for (String i : eachLine) {
                String[] eachValue = i.split(",");
                if (eachValue[0].equals(id)) {
                    setCustomerID(id);
                    setName(eachValue[2]);
                    setAccount(eachValue[3]);
                    setBalance(Float.parseFloat(eachValue[4]));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void withdraw(float amount) {
        boolean flag = false;
        if ((getBalance() - amount) >= 0) {
            setBalance(getBalance() - amount);
            flag = true;
            System.out.println("\u09F3 " + amount + "/- has been withdrawn. Current Balance is \u09F3 " + getBalance() + "/-");
        } else System.out.println("Insufficient Balance!");
        makeTransaction("WITHDRAW", amount, flag);
        updateData();

    }

    protected void deposit(float amount) {
        boolean flag = false;
        if (amount >= 0) {
            setBalance(getBalance() + amount);
            flag = true;
            System.out.println("\u09F3 " + amount + "/- has been deposited. Current Balance is \u09F3 " + getBalance() + "/-");
        }
        makeTransaction("DEPOSIT", amount, flag);
        updateData();
    }

    protected void makeTransaction(String trxType, float amount, boolean flag) {
        generateTrxID();
        try {
            FileWriter file = new FileWriter(super.transactionData, true);
            String trx = getCustomerID() + "," + getName() + "," + getAccount() + "," + getTransactionID() + "," +
                    trxType + "," + amount + "," + getBalance() + "," + (flag ? "SUCCEED" : "FAILED") + "\n";
            file.write(trx);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected void viewDetails() {
        //Setting attributes
        List<String> attributes = new ArrayList<>();
        attributes.add("C_ID");
        attributes.add("NAME");
        attributes.add("ACCOUNT NUMBER");
        attributes.add("BALANCE (\u09F3)");

        List<String> values = new ArrayList<>();
        values.add(getCustomerID());
        values.add(getName());
        values.add(getAccount());
        values.add(String.valueOf(getBalance()));

        List<List<String>> rowList = new ArrayList<>();
        rowList.add(values);

        String tableName = "ACCOUNT INFORMATION: ";
        TableGenerator table = new TableGenerator();
        System.out.print(table.generateTable(attributes, tableName, rowList));
    }

    protected void updateData() {
        File fileToBeModified = new File(super.accountData);
        try {
            String[] eachLine = super.readFile(super.accountData);
            StringBuilder newContent = new StringBuilder();
            for (String i : eachLine) {
                String[] eachValue = i.split(",");
                if (eachValue[0].equals(getCustomerID())) {
                    newContent.append(eachValue[0])
                            .append(",").append(eachValue[1]).append(",").append(eachValue[2])
                            .append(",").append(eachValue[3]).append(",").append(getBalance());
                } else newContent.append(i);
                newContent.append("\r\n");
            }

            FileWriter file = new FileWriter(fileToBeModified);
            file.write(newContent.toString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void createAccount(String c_id) {
        try {
            String[] eachLine = super.readFile(super.customerData);
            for (String i : eachLine) {
                String[] eachValue = i.split(",");
                if (eachValue[0].equals(c_id)) {
                    setCustomerID(eachValue[0]);
                    setName(eachValue[1].toUpperCase());
                    setPassword(eachValue[3]);
                    generateAccount();
                    setBalance(Float.parseFloat(eachValue[4]));
                }
            }

            //Previous existence
            eachLine = super.readFile(super.accountData);
            boolean flag = true;
            try {
                for (String i : eachLine) {
                    String[] eachValue = i.split(",");
                    if (eachValue[0].equals(c_id)) {
                        flag = false;
                        break;
                    }
                }
            } catch (NumberFormatException e) {
                //Empty File
            }
            System.out.println("============================================================");
            if (flag) dataEntry();
            else System.out.println("Status: C_ID(" + getCustomerID() + ") has been already verified.");
            System.out.println("============================================================");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Delete an account

    private void dataEntry() {
        try {
            FileWriter file = new FileWriter(super.accountData, true);
            file.write(getCustomerID() + "," + getPassword() + "," + getName() + "," + getAccount() + "," + getBalance() + "\n");
            file.close();
            makeTransaction("STARTING BALANCE", getBalance(), true);
            System.out.println("Status: " + getName() + "(C_ID-" + getCustomerID() + ") has been verified.");
        } catch (IOException ignored) {
            System.out.println("Status: Verification could not be processed due to an error.");
        }
    }

    private void generateTrxID() {
        Random rand = new Random();
        StringBuilder transactionID = new StringBuilder("TrxID");
        for (int i = 0; i < 8; i++) {
            int n = rand.nextInt(10);
            transactionID.append(n);
        }
        try {
            String[] eachLine = super.readFile(super.transactionData);
            for (String i : eachLine) {
                String[] eachValue = i.split(",");
                if (eachValue[0].equals(transactionID.toString())) generateTrxID();
                else setTransactionID(transactionID.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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

    protected void showTransaction() {
        //Setting attributes
        List<String> attributes = new ArrayList<>();
        attributes.add("C_ID");
        attributes.add("NAME");
        attributes.add("ACCOUNT NUMBER");
        attributes.add("TRX_ID");
        attributes.add("TRANSACTION TYPE");
        attributes.add("AMOUNT (\u09F3)");
        attributes.add("BALANCE (\u09F3)");
        attributes.add("STATUS (\u09F3)");

        //Setting values
        try {
            Scanner input = new Scanner(new File(transactionData));
            String tableName = "TRANSACTION DETAILS: ";
            settingRowValues(input, tableName, getCustomerID(), attributes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void settingRowValues(Scanner input, String tableName, String customerID, List<String> attributes) {
        List<List<String>> rowsList = new ArrayList<>();
        while (input.hasNextLine()) {
            List<String> userData = Arrays.asList(input.nextLine().split(","));
            if (userData.get(0).equals(customerID)) rowsList.add(userData);
        }
        input.close();
        TableGenerator table = new TableGenerator();
        System.out.print(table.generateTable(attributes, tableName, rowsList));
    }
}
