import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

class restaurantApp {
    public static void main(String[] args) {
        GetFileData objectGetFileData = new GetFileData();
        String[][] outputData = objectGetFileData.getCompeteDataINArray("menuList.csv", 3);
        System.out.println(outputData[10][1]);
        int lastID =  objectGetFileData.getLastID("menuList.csv", 3);
        System.out.println("Last ID: " + lastID);
    }
}

class GetFileData {
    public String[][] getCompeteDataINArray(String fileName, int countData) {
        String[][] completeFileDataArray = null;

        try {
            File fileReader = new File(fileName);
            Scanner scFileReader = new Scanner(fileReader);

            ArrayList<String> completeFileData = new ArrayList<String>();

            while (scFileReader.hasNext()) {
                String eachLine = scFileReader.nextLine();
                completeFileData.add(eachLine);
            }

            int sizeCompleteFileData = completeFileData.size();
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

            // System.out.println(completeFileDataArray[10][2]);

            scFileReader.close();
        } catch (Exception e) {
            System.out.println("Error");
        }
        return completeFileDataArray;
    }

    public int getLastID(String fileName, int countData){
        int lastID = 0;
        String[][] outputData =  this.getCompeteDataINArray(fileName,countData);
        lastID = Integer.valueOf(outputData[outputData.length-1][0]) ;
        return lastID;
    }



}
