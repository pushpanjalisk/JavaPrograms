import java.io.File;

public class createNewFolder {
    public static void main(String[] args) {
        try {
            String folderName = "subfolder-JavaPrograms";          
            File fileObject = new File(folderName);
            if(fileObject.exists()){
                System.out.println("Cannot create a Folder, Folder-Name is already taken");
            }else{
                fileObject.mkdir();
                System.out.println("New folder is created\n" + fileObject.getAbsolutePath());
            }
       } catch (Exception e) {
            System.out.println("run time error");    
    }
    }
}
