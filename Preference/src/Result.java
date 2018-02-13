import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class Result extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp){
        try {
            PrintWriter printWriter = resp.getWriter();
            printWriter.println("get");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp){
        Recommend recommend = Recommend.getTempExcutedIntance();
        String[] result = recommend.getResult().split("ESC");
        PrintWriter printWriter = null;

        try {
            printWriter = resp.getWriter();
            printWriter.println("post");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String e : result) {
            printWriter.println(e);
        }

    }
}
