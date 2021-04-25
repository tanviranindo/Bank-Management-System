//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Scanner;
//
//public class Transaction extends Account {
//    static String transactionData = "C:\\Users\\Tanvir\\IdeaProjects\\Bank Management System\\src\\transaction.txt";
//
//    public static void showAllTransaction() {
//        //Setting attributes
//        List<String> attributes = new ArrayList<>();
//        attributes.add("TRX_ID");
//        attributes.add("C_ID");
//        attributes.add("NAME");
//        attributes.add("ACCOUNT NUMBER");
//        attributes.add("TRANSACTION TYPE");
//        attributes.add("AMOUNT (\u09F3)");
//        attributes.add("BALANCE (\u09F3)");
//
//        //Setting values
//        try {
//            Scanner input = new Scanner(new File(transactionData));
//            String tableName = "TRANSACTION DETAILS: ";
//            settingRowValues(input, tableName, attributes);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void settingRowValues(Scanner input, String tableName, List<String> attributes) {
//        List<List<String>> rowsList = new ArrayList<>();
//        while (input.hasNextLine()) {
//            rowsList.add(Arrays.asList(input.nextLine().split(",")));
//            System.out.println(rowsList);
//        }
//        input.close();
//        TableGenerator table = new TableGenerator();
//        System.out.print(table.generateTable(attributes, tableName, rowsList));
//    }
//
//    public static void main(String[] args) {
//        showAllTransaction();
//    }
//
//}
