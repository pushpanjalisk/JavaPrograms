
import java.util.Arrays;
import java.util.Scanner;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class restaurantApp {
    public static Scanner sc = new Scanner(System.in);
    public static GetFileData menuListGetFileData = new GetFileData("menuList.csv");
    public static GetFileData orderDetailsGetFileData = new GetFileData("orderDetails.csv");

    public static void main(String[] args) {
        int option;
        char choice = 'Y';

        do {
            System.out.println("\nWelcome to Quick-Bites Restaurant, Hyderabad");
            System.out.println("\n1. Enter new Order\n2. Cancel orders\n3. See Collection of a Day");
            System.out.print("\n\nEnter your choice: ");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    generateNewOrder();
                    break;

                case 2:
                    cancelOrder();
                    break;

                case 3:
                    getTotalCollectionOFTheDay();
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
        menuListGetFileData.displayCompleteData();

        System.out.println("\n");
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

        System.out.println("\nPlease check the Order details:\n");

        double billAmount = 0;

        for (int i = 0; i < foodPlan.length; i += 2) {
            String menuName[] = menuListGetFileData.searchIDINFile(Integer.toString(foodPlan[i]));
            System.out.println(menuName[1] + ":\t" + foodPlan[i + 1] + " Qty" + "\tPrice: " + menuName[2]);
            billAmount += ((Double.parseDouble(menuName[2])) * foodPlan[i + 1]);
        }
        System.out.println("Total Bill Amount: " + billAmount);

        System.out.print("\nPress 'Y' to confirm the Order: ");
        char orderConfirmation = sc.next().charAt(0);
        if (orderConfirmation == 'Y' || orderConfirmation == 'y') {
            saveFoodOrder(foodPlan, billAmount);
        } else {
            System.out.println("Order was not saved!");
        }
    }

    static void saveFoodOrder(int[] foodPlan, double billAmount) {
        String orderDetails = "";
        for (int i = 0; i < foodPlan.length; i++) {
            if(i == foodPlan.length-1){
                orderDetails += (Integer.toString(foodPlan[i]));
            }else{
                orderDetails += (Integer.toString(foodPlan[i]) + " ");
            }
            
        }

        String orderDetailsFileInput = "\n" + Integer.toString(orderDetailsGetFileData.getLastID() + 1) + ","
                + DateTimeFormatter.ofPattern("YYYY-dd-MMM").format(LocalDateTime.now()) + "," + billAmount + ","
                + orderDetails + ",Approved";
        try {

            FileOutputStream objectSaveDataINFile = new FileOutputStream("orderDetails.csv", true);
            byte[] inputData = orderDetailsFileInput.getBytes();

            objectSaveDataINFile.write(inputData);
            objectSaveDataINFile.close();
            orderDetailsGetFileData = new GetFileData("orderDetails.csv");
        } catch (Exception e) {
            System.out.println("Order was not saved. Please enter details again!");
        }

    }

    static void cancelOrder() {
        orderDetailsGetFileData.displayCompleteData();

        System.out.println("\n");
        System.out.print("Enter the Order Id to be canceled: ");
        int orderID = sc.nextInt();
        System.out.println(Arrays.toString(orderDetailsGetFileData.searchIDINFile(Integer.toString(orderID))));

        System.out.print("\nPress 'C' to Cancel the Order: ");
        char cancelConfirmation = sc.next().charAt(0);
        if (cancelConfirmation == 'C' || cancelConfirmation == 'c') {
            String[][] completeOrderData = orderDetailsGetFileData.getCompleteFileDataINArray();
            for (int i = 0; i < completeOrderData.length; i++) {
                if (i == orderDetailsGetFileData.searchIndex) {
                    completeOrderData[i][4] = "Canceled";
                    System.out.println("Order Canceled: " + Arrays.toString(completeOrderData[i]));
                }
            }
            try {
                FileOutputStream objectSaveDataINFile = new FileOutputStream("orderDetails.csv");
                objectSaveDataINFile.close();

                byte[] inputData = new byte[0];

                objectSaveDataINFile = new FileOutputStream("orderDetails.csv", true);
                for (int i = 0; i < completeOrderData.length; i++) {
                    String[] eachOrderDetails = completeOrderData[i];
                    String stringEachOrderDetails = "";
                    for (int j = 0; j < eachOrderDetails.length; j++) {
                        if (j == eachOrderDetails.length - 1) {
                            stringEachOrderDetails += (eachOrderDetails[j]);
                        } else {
                            stringEachOrderDetails += (eachOrderDetails[j] + ",");
                        }
                    }
                    if (i == completeOrderData.length - 1) {

                    } else {
                        stringEachOrderDetails += "\n";
                    }

                    inputData = stringEachOrderDetails.getBytes();
                    objectSaveDataINFile.write(inputData);
                }
                objectSaveDataINFile.close();
                orderDetailsGetFileData = new GetFileData("orderDetails.csv");
            } catch (Exception e) {
                System.out.println("Error in Saving data");
            }
        } else {
            System.out.println("Order not Canceled!");
        }
    }


    static void getTotalCollectionOFTheDay(){
        double totalCollectionOFDay = 0;
        boolean dateFound = false;

        System.out.print("Enter the date in format (YYYY-DD-MMM): ");
        String inputDate = sc.next();

        String[][] outputData = orderDetailsGetFileData.getCompleteFileDataINArray();
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