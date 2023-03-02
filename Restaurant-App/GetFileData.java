import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.util.Scanner;

public class GetFileData {
    String fileName = "";
    int countData = 0;
    int sizeCompleteFileData = 0;
    File fileReader;
    Scanner scFileReader;
    int searchIndex = 0;

    ArrayList<String> completeFileData = new ArrayList<String>();

    GetFileData(String fileName) {
        this.fileName = fileName;
        try {
            this.fileReader = new File(fileName);
            this.scFileReader = new Scanner(fileReader);

            while (this.scFileReader.hasNext()) {
                String eachLine = this.scFileReader.nextLine();
                completeFileData.add(eachLine);
            }

            if (fileName == "menuList.csv")
                this.countData = 3;
            else if (fileName == "orderDetails.csv")
                this.countData = 5;

        } catch (Exception e) {
            System.out.println("Unable to open the file: " + fileName);
        }
        scFileReader.close();
    }

    String[][] getCompleteFileDataINArray() {
        String[][] completeFileDataArray = null;

        this.sizeCompleteFileData = this.completeFileData.size();
        completeFileDataArray = new String[sizeCompleteFileData][countData];

        for (int i = 0; i < sizeCompleteFileData; i++) {
            String dataLineOne = completeFileData.get(i);
            for (int j = 0; j < countData; j++) {
                if (j == countData - 1) {
                    completeFileDataArray[i][j] = dataLineOne;
                } else {
                    completeFileDataArray[i][j] = dataLineOne.substring(0, dataLineOne.indexOf(","));
                    dataLineOne = dataLineOne.substring(dataLineOne.indexOf(",") + 1);
                }
            }
        }
        return completeFileDataArray;
    }

    public int getLastID() {
        String[][] completeFileDataArray = this.getCompleteFileDataINArray();
        int lastID = 0;
        lastID = Integer.valueOf(completeFileDataArray[completeFileDataArray.length - 1][0]);
        return lastID;
    }

    public String[] searchIDINFile(String searchID) {
        String[][] completeFileDataArray = this.getCompleteFileDataINArray();
        String outputArray[] = new String[this.countData];

        for (int i = 0; i < sizeCompleteFileData; i++) {
            if (searchID.equals(completeFileDataArray[i][0])) {
                outputArray = completeFileDataArray[i];
                this.searchIndex = i;
            }
        }
        return outputArray;
    }

    public void displayCompleteData() {
        String[][] completeFileDataArray = this.getCompleteFileDataINArray();
        for (String[] lineItem : completeFileDataArray) {
            System.out.println(Arrays.toString(lineItem));
        }
    }
}