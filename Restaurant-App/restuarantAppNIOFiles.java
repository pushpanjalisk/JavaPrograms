import java.io.FileOutputStream;
import java.util.Scanner;
import java.util.Arrays;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

class FoodOrder {
    GetDataFromFile objectGDFF = new GetDataFromFile();
    String orderID;
    String orderStatus;

    String orderDate;
    String orderDetails = "";
    double billAmount = 0;

    String orderDetailsFileInput = "";

    FoodOrder(int[] foodPlan) {

        this.orderID = Integer.toString((objectGDFF.getLastID("orderDetails.csv", 3)) + 1);

        for (int num : foodPlan) {
            this.orderDetails += (Integer.toString(num) + " ");
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-dd-MMM");
        LocalDateTime now = LocalDateTime.now();
        this.orderDate = dtf.format(now);

        // System.out.println("\nOrder details:");
        for (int i = 0; i < foodPlan.length; i += 2) {
            
            String[] menuDetails = objectGDFF.searchDataINFile("menuList.csv", Integer.toString(foodPlan[i]), 3);

            System.out.println(menuDetails[1] + "\t:\t" + menuDetails[2] + "\t:\t" + foodPlan[i + 1] + " Qty");
            billAmount += ((Double.parseDouble(menuDetails[2])) * foodPlan[i + 1]);
        }
        System.out.println("Total Bill Amount: " + this.billAmount);
        this.orderDetailsFileInput = ("\n" + this.orderID + "," + this.orderDate + "," + this.billAmount + ","
                + this.orderDetails);
    }

    FoodOrder(String replaceText, int replaceIndex) {
        try {
            List<String> fileData = Files.readAllLines(Paths.get("orderDetails.csv"));
            for (int i = 0; i < fileData.size(); i++) {
                if (i == replaceIndex - 1) {
                    fileData.set(i, replaceText);
                }
            }
            FileOutputStream objectSaveDataINFile = new FileOutputStream("orderDetails.csv");
            byte[] inputData = new byte[0];
            objectSaveDataINFile.close();
            objectSaveDataINFile = new FileOutputStream("orderDetails.csv", true);
            for (String eachLine : fileData) {
                eachLine += "\n";
                inputData = eachLine.getBytes();
                objectSaveDataINFile.write(inputData);
            }
            objectSaveDataINFile.close();
        } catch (Exception e) {
            System.out.println("Order was not saved. Please enter details again!");
        }
    }

    void saveOrderTOFile() {
        try {
            this.orderDetailsFileInput += "," + "Approved";
            FileOutputStream objectSaveDataINFile = new FileOutputStream("orderDetails.csv", true);
            byte[] inputData = this.orderDetailsFileInput.getBytes();

            objectSaveDataINFile.write(inputData);
            objectSaveDataINFile.close();
        } catch (Exception e) {
            System.out.println("Order was not saved. Please enter details again!");
        }

    }
}

class restuarantAppNIOFiles {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int option;
        char choice = 'Y';

        do {
            System.out.println("\nWelcome to Quick-Bites Restaurant, Hyderabad");
            System.out.println("\n1. Enter new Order\n2. Edit Bill Status\n3. See Collection of a Day");
            System.out.print("\n\nEnter your choice: ");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    generateNewOrder();
                    break;

                case 2:
                    editOrderStatus();
                    break;

                case 3:
                    getTotalCollection();
                    break;

                default:
                    System.out.println("Invalid input!");
                    break;
            }

            System.out.print("\n\nPress 'Y' to go back to the Main-Restaurant Menu: ");
            choice = sc.next().charAt(0);
        } while (choice == 'Y' || choice == 'y');
        sc.close();
    }

    static void generateNewOrder() {
        System.out.print("Enter the Count Details: ");
        int count = sc.nextInt();
        int[] foodPlan = new int[count * 2];

        int index = 0;
        for (int i = 1; i <= count; i++) {
            System.out.print("\nItemNumber - " + i + ": Enter MenuID: ");
            foodPlan[index] = sc.nextInt();
            index++;
            System.out.print("Enter Quantity: ");
            foodPlan[index] = sc.nextInt();
            index++;
        }
        
        System.out.println("\nPlease check and confirm the Order details:\n");
        FoodOrder objectOrder = new FoodOrder(foodPlan);
        System.out.print("\nPress 'Y' to confirm the Order: ");
        char orderStatus = sc.next().charAt(0);
        if (orderStatus == 'Y' || orderStatus == 'y') {
            objectOrder.saveOrderTOFile();
        } else {
            objectOrder = null;
        }
    }

    static void editOrderStatus() {
        System.out.print("Enter the Order ID: ");
        String inputOrderID = Integer.toString(sc.nextInt());
        GetDataFromFile objectGDFF = new GetDataFromFile();

        String[] outputData = objectGDFF.searchDataINFile("orderDetails.csv", inputOrderID, 5);
        System.out.println("\n" + Arrays.toString(outputData));
        System.out.print("Press 'C' to CANCEL the Order: ");
        char orderStatus = sc.next().charAt(0);
        if (orderStatus == 'C' || orderStatus == 'c') {
            String replaceText = "";
            outputData[outputData.length - 1] = "Canceled";
            for (String detail : outputData)
                replaceText += (detail + ",");
            FoodOrder objectFoodOrder = new FoodOrder(replaceText, objectGDFF.searchIndex);
        }
    }

    static void getTotalCollection() {
        double totalCollectionOFDay = 0;
        boolean dateFound = false;
        GetDataFromFile objGetDataFromFile = new GetDataFromFile();
        String[][] outputData = objGetDataFromFile.getCompleteData("orderDetails.csv", 5);

        System.out.print("Enter the date in format (YYYY-DD-MMM): ");
        String inputDate = sc.next();
        
        for (int i = 0; i < outputData.length; i++) {
            if (inputDate.equals(outputData[i][1]) &&  (outputData[i][4]).equals("Approved") ) {
                totalCollectionOFDay += Double.valueOf(outputData[i][2]);
                dateFound = true;
            }
        }
        if (dateFound == true)
            System.out.println(inputDate + " day's total Collection is: " + totalCollectionOFDay);
        else
            System.out.println("No data found!");
    }
}

class GetDataFromFile {
    int searchIndex = 0;

    String[] searchDataINFile(String fileName, String searchString, int dataCount) {
        String[] outputData = new String[dataCount];
        try {
            boolean dataFoundFlag = false;
            List<String> fileData = Files.readAllLines(Paths.get(fileName));

            for (String eachLine : fileData) {
                String menuID = eachLine.substring(0, (eachLine.indexOf(",")));
                this.searchIndex++;
                if (menuID.equals(searchString)) {
                    for (int i = 0; i < dataCount; i++) {
                        if (i == dataCount - 1) {
                            outputData[i] = eachLine;
                        } else {
                            outputData[i] = eachLine.substring(0, (eachLine.indexOf(",")));
                            eachLine = eachLine.substring(eachLine.indexOf(",") + 1);
                        }
                    }
                    dataFoundFlag = true;
                    break;
                }
            }

            if (dataFoundFlag == false) {
                System.out.println("Data not found: " + searchString);
            }
        } catch (Exception e) {
            System.out.println("Error while reading the file - " + fileName + ": " + e.getMessage());
        }
        return outputData;
    }

    int getLastID(String fileName, int dataCount) {
        int lastID = 0;
        try {
            List<String> fileData = Files.readAllLines(Paths.get(fileName));
            String lastDataLine = fileData.get(fileData.size() - 1);
            lastID = Integer.valueOf(lastDataLine.substring(0, (lastDataLine.indexOf(","))));
        } catch (Exception e) {
            System.out.println("File not found: " + fileName);
        }
        return lastID;
    }

    String[][] getCompleteData(String fileName, int dataCount) {
        String completeFileData[][] = null;
        int index = 0;
        try {
            List<String> fileData = Files.readAllLines(Paths.get(fileName));
            completeFileData = new String[fileData.size()][dataCount];
            for (String eachLine : fileData) {
                for (int i = 0; i < dataCount; i++) {
                    if (i == dataCount - 1) {
                        completeFileData[index][i] = eachLine;
                    } else {
                        completeFileData[index][i] = eachLine.substring(0, (eachLine.indexOf(",")));
                        eachLine = eachLine.substring(eachLine.indexOf(",") + 1);
                    }
                }
                index++;
            }
        } catch (Exception e) {
            System.out.println("File not found: " + fileName);
        }
        return completeFileData;
    }
}