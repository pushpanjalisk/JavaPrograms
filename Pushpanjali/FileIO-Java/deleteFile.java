import java.io.File;

public class deleteFile {
    public static void main(String[] args) {
        String fileName = "demoOne.txt";
        File fileObject = new File(fileName);
        
        if(fileObject.delete()){
            System.out.println("File deleted sucessfully");
        }else{
            System.out.println("Error");
        }

    }
}
