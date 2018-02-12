import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Recommend {
    private String result;
    public static Recommend getInstance(){
        return new Recommend();
    }

    public static Recommend getTempExcutedIntance(){
        Recommend recommend = new Recommend();
        try {
            recommend.execute("python3 /home/voidbluelabtop/Desktop/python/Preferenceproject/Recommend.py");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return recommend;
    }
    private Recommend(){

    }

    public void execute(String shellCommand) throws IOException {
        Process p = null;
        try{
            p= Runtime.getRuntime().exec(shellCommand);
            p.waitFor();
        } catch (IOException ex){
            System.out.println(ex.getMessage());} catch (InterruptedException e) {
            e.printStackTrace();
        }
        StringBuffer sb = new StringBuffer();
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String str;
        while((str=br.readLine())!=null){
            sb.append(str);
        }
        result = sb.toString();
    }

    public String getResult(){
        return result;
    }
}
