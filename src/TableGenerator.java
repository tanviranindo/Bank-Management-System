import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableGenerator {

    private final int PADDING_SIZE = 2;

    public String generateTable(List<String> headersList, List<List<String>> rowsList, int... overRiddenHeaderHeight) {
        StringBuilder stringBuilder = new StringBuilder();

        int rowHeight = overRiddenHeaderHeight.length > 0 ? overRiddenHeaderHeight[0] : 1;

        Map<Integer, Integer> columnMaxWidthMapping = getMaximumWidthTable(headersList, rowsList);

        String NEW_LINE = "\n";
        stringBuilder.append(NEW_LINE);
        stringBuilder.append(NEW_LINE);
        createRowLine(stringBuilder, headersList.size(), columnMaxWidthMapping);
        stringBuilder.append(NEW_LINE);


        for (int headerIndex = 0; headerIndex < headersList.size(); headerIndex++) {
            fillCell(stringBuilder, headersList.get(headerIndex), headerIndex, columnMaxWidthMapping);
        }

        stringBuilder.append(NEW_LINE);

        createRowLine(stringBuilder, headersList.size(), columnMaxWidthMapping);


        for (List<String> row : rowsList) {

            stringBuilder.append(NEW_LINE.repeat(Math.max(0, rowHeight)));

            for (int cellIndex = 0; cellIndex < row.size(); cellIndex++) {
                fillCell(stringBuilder, row.get(cellIndex), cellIndex, columnMaxWidthMapping);
            }

        }

        stringBuilder.append(NEW_LINE);
        createRowLine(stringBuilder, headersList.size(), columnMaxWidthMapping);
        stringBuilder.append(NEW_LINE);
        stringBuilder.append(NEW_LINE);

        return stringBuilder.toString();
    }

    private void fillSpace(StringBuilder stringBuilder, int length) {
        stringBuilder.append(" ".repeat(Math.max(0, length)));
    }

    private void createRowLine(StringBuilder stringBuilder, int headersListSize, Map<Integer, Integer> columnMaxWidthMapping) {
        for (int i = 0; i < headersListSize; i++) {
            String TABLE_JOINT_SYMBOL = "+";
            if (i == 0) {
                stringBuilder.append(TABLE_JOINT_SYMBOL);
            }

            String TABLE_H_SPLIT_SYMBOL = "-";
            stringBuilder.append(TABLE_H_SPLIT_SYMBOL.repeat(Math.max(0, columnMaxWidthMapping.get(i) + PADDING_SIZE * 2)));
            stringBuilder.append(TABLE_JOINT_SYMBOL);
        }
    }


    private Map<Integer, Integer> getMaximumWidthTable(List<String> headersList, List<List<String>> rowsList) {
        Map<Integer, Integer> columnMaxWidthMapping = new HashMap<>();

        for (int columnIndex = 0; columnIndex < headersList.size(); columnIndex++) {
            columnMaxWidthMapping.put(columnIndex, 0);
        }

        for (int columnIndex = 0; columnIndex < headersList.size(); columnIndex++) {

            if (headersList.get(columnIndex).length() > columnMaxWidthMapping.get(columnIndex)) {
                columnMaxWidthMapping.put(columnIndex, headersList.get(columnIndex).length());
            }
        }


        for (List<String> row : rowsList) {

            for (int columnIndex = 0; columnIndex < row.size(); columnIndex++) {

                if (row.get(columnIndex).length() > columnMaxWidthMapping.get(columnIndex)) {
                    columnMaxWidthMapping.put(columnIndex, row.get(columnIndex).length());
                }
            }
        }

        for (int columnIndex = 0; columnIndex < headersList.size(); columnIndex++) {

            if (columnMaxWidthMapping.get(columnIndex) % 2 != 0) {
                columnMaxWidthMapping.put(columnIndex, columnMaxWidthMapping.get(columnIndex) + 1);
            }
        }


        return columnMaxWidthMapping;
    }

    private int getOptimumCellPadding(int cellIndex, int datalength, Map<Integer, Integer> columnMaxWidthMapping, int cellPaddingSize) {
        if (datalength % 2 != 0) {
            datalength++;
        }

        if (datalength < columnMaxWidthMapping.get(cellIndex)) {
            cellPaddingSize = cellPaddingSize + (columnMaxWidthMapping.get(cellIndex) - datalength) / 2;
        }

        return cellPaddingSize;
    }

    private void fillCell(StringBuilder stringBuilder, String cell, int cellIndex, Map<Integer, Integer> columnMaxWidthMapping) {

        int cellPaddingSize = getOptimumCellPadding(cellIndex, cell.length(), columnMaxWidthMapping, PADDING_SIZE);

        String TABLE_V_SPLIT_SYMBOL = "|";
        if (cellIndex == 0) {
            stringBuilder.append(TABLE_V_SPLIT_SYMBOL);
        }

        fillSpace(stringBuilder, cellPaddingSize);
        stringBuilder.append(cell);
        if (cell.length() % 2 != 0) {
            stringBuilder.append(" ");
        }

        fillSpace(stringBuilder, cellPaddingSize);

        stringBuilder.append(TABLE_V_SPLIT_SYMBOL);

    }

    public void generateTable(Process[] process, int jobCount, int type) {
        TableGenerator tableGenerator = new TableGenerator();

        List<String> headersList = new ArrayList<>();
        headersList.add("Process ID");
        if (type != 3) headersList.add("Arrival Time");
        headersList.add("Burst Time");
        if (type == 2) headersList.add("Priority");
        headersList.add("Completion Time");
        headersList.add("Waiting Time");
        headersList.add("Turn Around Time");

        List<List<String>> rowsList = new ArrayList<>();

//        for (int i = 0; i < jobCount; i++) {
//            List<String> row = new ArrayList<>();
//            row.add(Integer.toString(process[i].getId()));
//            if (type != 3) row.add(Integer.toString(process[i].getArrivalTime()));
//            row.add(Integer.toString(process[i].getBurstTime()));
//            if (type == 2) row.add(Integer.toString(process[i].getPriority()));
//            row.add(Integer.toString(process[i].getCompletionTime()));
//            row.add(Integer.toString(process[i].getWaitingTime()));
//            row.add(Integer.toString(process[i].getTurnAroundTime()));
//            rowsList.add(row);
//        }

//        System.out.print(tableGenerator.generateTable(headersList, rowsList));
    }
}