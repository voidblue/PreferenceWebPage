import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Scanner;

public class callpythonTest {

    @Test
    public void test(){
        try{
            String path = getClass().getResource("").getPath();
            System.out.println(path);
//            String prg = "import sys\nprint int(sys.argv[1])+int(sys.argv[2])\n";
            BufferedWriter out = new BufferedWriter(new FileWriter(path+"/Preferenceproject/RecommendRunner.py"));
//            out.write(prg);
            out.close();
//            int number1 = 10;
//            int number2 = 32;
            ProcessBuilder pb = new ProcessBuilder("python3");
            System.out.println(pb.directory());
            Process p = pb.start();

            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
//            int ret = new Integer(in.readLine());
            String ret = in.readLine();
            System.out.println("value is : "+ret);
        }catch(Exception e){System.out.println(e.getMessage());}

        File file = new File("/home/voidbluelabtop/Desktop/javaprogramming/Preference/test/Preferenceproject/output");
        System.out.println(file.toString());
    }

//
//    @Test
//    public void test2() throws IOException {
//        Process p = null;
//        try{
//            p= Runtime.getRuntime().exec("python3 /home/voidbluelabtop/Desktop/python/Preferenceproject/RecommendRunner.py");
//            p.waitFor();
//        } catch (IOException ex){
//            System.out.println(ex.getMessage());} catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        StringBuffer sb = new StringBuffer();
//        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
//        Scanner scanner = new Scanner(br);
//        String str = null;
//        while((str=br.readLine())!=null){
//            sb.append(str);
//        }
//        String result = sb.toString();
//        result = result.split("startESC")[1];
//        result.split("ESC");
//        System.out.println(result.split("ESC")[0]);
//    }
//

}
