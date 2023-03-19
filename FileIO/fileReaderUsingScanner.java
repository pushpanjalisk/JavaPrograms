import java.io.File;
import java.io.FileOutputStream;
import java.util.Scanner;

//File reading using Scaneer class 
public class fileReaderUsingScanner {
    public static void main(String[] args) {
        try {
            File studentFile = new File("heroVired.txt");
            Scanner sc = new Scanner(studentFile);
            System.out.println("File data before addition: ");
            while (sc.hasNext()) {
                String data = sc.nextLine();
                System.out.println(data);
            }

            String newData = "\nCertificate Program in Full Stack Development with Cloud for Web and Mobile \n1-month Bootcamp on Programming Fundamentals, \nOffers MERN Stack and Java Spring Boot as specializations";
            
            FileOutputStream fileSaveObject = new FileOutputStream(studentFile,true);
            fileSaveObject.write(newData.getBytes());
            fileSaveObject.close();
            

            System.out.println("File data after addition: ");
            sc.close();
            sc = new Scanner(studentFile);
            while (sc.hasNext()) {
                String data = sc.nextLine();
                System.out.println(data);
            }

            sc.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }
}