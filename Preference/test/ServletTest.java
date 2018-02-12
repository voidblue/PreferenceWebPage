import org.junit.jupiter.api.Test;

import java.io.*;

public class ServletTest {
    @Test
    public void test(){
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("/home/voidbluelabtop/Desktop/javaprogramming/Preference/src/poll.html");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        try {
            System.out.println(bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
