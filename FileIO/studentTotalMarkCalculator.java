import java.io.File;
import java.util.Scanner;

//File reading using Scaneer class 
public class studentTotalMarkCalculator{
    public static void main(String[] args) {
        try {
            File studentFile = new File("studentDB.csv");
            Scanner sc = new Scanner(studentFile);
            
            while (sc.hasNext()) {
                int totalMarks = 0;
                String data = sc.nextLine();

                int studentID = Integer.valueOf(data.substring(0, (data.indexOf(","))));
                
                    // System.out.println(data);
                    data = data.substring(data.indexOf(",") + 1);
                    String studentName = (data.substring(0, (data.indexOf(","))));
                    
                    data = data.substring(data.indexOf(",") + 1);
                    int science = Integer.valueOf(data.substring(0, (data.indexOf(","))));
                    
                    data = data.substring(data.indexOf(",") + 1);
                    int maths = Integer.valueOf(data.substring(0, (data.indexOf(","))));
                    
                    data = data.substring(data.indexOf(",") + 1);
                    int socialScience = Integer.valueOf(data.substring(0, (data.indexOf(","))));
                    
                    data = data.substring(data.indexOf(",") + 1);
                    int language1 = Integer.valueOf(data.substring(0, (data.indexOf(","))));
                    
                    data = data.substring(data.indexOf(",") + 1);
                    int language2 = Integer.valueOf(data);                
                    totalMarks += (science + maths + socialScience + language1 + language2);

                    System.out.println("ID: " + studentID + "\tName: " + studentName + "\nTotal Marks: " + totalMarks + "\n\n");
            }
            sc.close();
           
        } catch (Exception e) {
            System.out.println(e.getMessage());
            
        }
    }
}