import java.io.File;
import java.io.FileOutputStream;

public class writeINFileNoAppend{
    public static void main(String[] args) {
        try {

            String newFileName = "sample.txt";
            File objFile = new File(newFileName);
            if (objFile.createNewFile()) {
                System.out.println("File is Created." + newFileName);
            }
            
            String inputText = "Hello World";
            byte[] byteInputText = inputText.getBytes();

            FileOutputStream fileWritingOutput = new FileOutputStream(objFile);
            fileWritingOutput.write(byteInputText);       
            
            fileWritingOutput.close();
            
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}
