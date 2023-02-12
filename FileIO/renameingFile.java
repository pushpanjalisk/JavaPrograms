import java.io.File;


public class renameingFile {
    public static void main(String[] args) {
        String fileNameOLD = "demoOne.txt";
        File fileObjectOLD = new File(fileNameOLD);
        
        String fileNameNEW = "newSample.txt";
        File fileObjectNEW = new File(fileNameNEW);

        
        if(fileObjectOLD.renameTo(fileObjectNEW)){
            System.out.println("File renamed sucessfully");
        }else{
            System.out.println("Error");
        }

    }
}

