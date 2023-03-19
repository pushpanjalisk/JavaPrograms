import java.util.Scanner;

public class simpleCalcy {
    public static void main(String[] args) {
        try{

        int operationChoice = 5;
        int result = 0;
        int numOne = 0, numTwo = 0;

        Scanner input = new Scanner(System.in);
        System.out.println("\n1. Addition:\n2. Subtraction:\n3. Multiplication:\n4. Division:");
        System.out.print("Please select the operation: ");

        operationChoice = input.nextInt();
        if (operationChoice >= 1 && operationChoice <= 4) {

            switch (operationChoice) {
                case 1:
                    System.out.print("Plese enter First Number for Addition: ");
                    numOne = input.nextInt();
                    System.out.print("Plese enter Second Number for Addition: ");
                    numTwo = input.nextInt();
                    result = (numOne + numTwo);
                    System.out.println("Addition of " + numOne + " and " + numTwo + " is: " + result);
                    break;

                case 2:
                    System.out.print("Plese enter First Number for Subtraction: ");
                    numOne = input.nextInt();
                    System.out.print("Plese enter Second Number for Subtraction: ");
                    numTwo = input.nextInt();
                    result = (numOne - numTwo);
                    System.out.println("Subtraction of " + numOne + " and " + numTwo + " is: " + result);
                    break;

                case 3:
                    System.out.print("Plese enter First Number for Multiplication: ");
                    numOne = input.nextInt();
                    System.out.print("Plese enter Second Number for Multiplication: ");
                    numTwo = input.nextInt();
                    result = (numOne * numTwo);
                    System.out.println("Multiplication of " + numOne + " and " + numTwo + " is: " + result);
                    break;

                case 4:
                    System.out.print("Plese enter First Number for Division: ");
                    numOne = input.nextInt();
                    System.out.print("Plese enter Second Number for Division: ");
                    numTwo = input.nextInt();
                    result = (numOne / numTwo);
                    System.out.println("Division of " + numOne + " and " + numTwo + " is: " + result);
                    break;

                default:
                    System.out.println("Invalid input");
                    break;
            }
        } else {
            System.out.println("Invalid input");
        }
        input.close();
    }catch (Exception e) {
        System.out.println(e.getMessage());
    }

    }
}