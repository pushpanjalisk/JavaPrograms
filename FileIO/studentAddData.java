import java.io.File;
import java.io.FileOutputStream;
import java.util.Scanner;

//File reading using Scaneer class 
public class studentAddData {
    public static void main(String[] args) {
        try {
            File studentFile = new File("studentDB.csv");
            Scanner sc = new Scanner(studentFile);
            String[] newStudentData = { "11", "Neha S", "22", "17", "20", "23", "18" };
            String data = "\n";
            for (int i = 0; i < newStudentData.length; i++) {
                if (i == newStudentData.length - 1)
                    data += (newStudentData[i]);
                else
                    data += (newStudentData[i] + ",");
            }
    
            FileOutputStream fileSaveObject = new FileOutputStream(studentFile,true);
            fileSaveObject.write(data.getBytes());
            fileSaveObject.close();

            while (sc.hasNext()) {
                data = sc.nextLine();
                System.out.println(data);
            }
            sc.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}