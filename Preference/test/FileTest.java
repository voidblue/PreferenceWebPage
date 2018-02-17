
import java.io.*;

public class FileTest {
    //파일이 잘 쓰이는지, 덮어쓰기가 잘 되는지 확인
    public static void main(String args[]){
        String path = System.getProperty("user.dir");
        System.out.print(path);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path + "/input");
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            bufferedOutputStream.write(path.getBytes());
            bufferedOutputStream.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
