import java.io.File;

public class createNewFile {
    public static void main(String[] args) {
       try {
            String fileName = "demo.txt";          
            File fileObject = new File(fileName);
            if(fileObject.exists()){
                System.out.println("Cannot create a File, File-Name is already taken");
            }else{
                fileObject.createNewFile();
                System.out.println("New file is created\n" + fileObject.getAbsolutePath());
            }
       } catch (Exception e) {
            System.out.println("run time error");    
    }
    }
}
