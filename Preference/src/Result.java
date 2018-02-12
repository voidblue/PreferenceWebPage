import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class Result extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp){

    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp){
        try{
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("1등", "");
        }catch(Exception e){System.out.println(e);}
    }
}
