import java.util.Arrays;
import java.util.Scanner;
import java.io.File;

public class getDataFromFileFunction {
    public static void main(String[] args) {

        int dataCount = 5;
        String searchString = "110";
        // String fileName = "menuList.txt";
        String fileName = "orderDetails.csv";

        String[] output = getDataFromFile(fileName, searchString, dataCount);
        System.out.println(Arrays.toString(output));
    }

    static String[] getDataFromFile(String fileName, String searchString, int dataCount) {
        String[] outputData = new String[dataCount];
        try {
            boolean dataFoundFlag = false;

            File menuList = new File(fileName);
            Scanner scFile = new Scanner(menuList);

            while (scFile.hasNext()) {
                String data = (scFile.nextLine());
                String menuID = data.substring(0, (data.indexOf(",")));

                if (menuID.equals(searchString)) {
                    for (int i = 0; i < dataCount; i++) {
                        if (i == dataCount - 1) {
                            outputData[i] = data;
                        } else {
                            outputData[i] = data.substring(0, (data.indexOf(",")));
                            data = data.substring(data.indexOf(",") + 1);
                        }

                    }
                    dataFoundFlag = true;
                    break;
                }
            }
            if (dataFoundFlag == false) {
                System.out.println("Data not found: " + searchString);
            }
            scFile.close();
            return outputData;
        } catch (Exception e) {
            System.out.println("Error while reading the file - menuList" + e.getMessage());
            return outputData;
        }

    }
}
