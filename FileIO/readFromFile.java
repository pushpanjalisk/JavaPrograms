import java.io.FileInputStream;
import java.io.File;

public class readFromFile {
    public static void main(String[] args) {
        try {
            File fileObject = new File("demo.txt");
            FileInputStream fileReadingObject = new FileInputStream(fileObject);
           
            int fileReadingIndex;
            String fileData = "";
            while ( (fileReadingIndex = fileReadingObject.read() ) != -1 ){
                fileData += ((char)fileReadingIndex);
                // System.out.print((char)fileReadingIndex);
            }

            fileReadingObject.close();
            System.out.println(fileData);
        } catch (Exception e) {
            System.out.println("runtime error");
        }

    }
}
