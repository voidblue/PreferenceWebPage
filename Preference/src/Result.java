import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class Result extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp){
        resp.setContentType("text/html; charset=euc-kr");
        try {
            PrintWriter printWriter = resp.getWriter();
            printWriter.println("get Method호출은 지원하지 않습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp){
        resp.setContentType("text/html; charset=euc-kr");
        PrintWriter printWriter = null;
        String path = System.getProperty("user.dir");
        try {
            printWriter = resp.getWriter();
            printWriter.println("post<br>");
            printWriter.println(path+ "<br>");
        } catch (IOException e) {
            e.printStackTrace();
        }


        Recommend recommend = Recommend.getTempExcutedIntance();
        String[] result = recommend.getResult().split("ESC");
        boolean isFirst = true;
        for (String e : result) {
            if (!isFirst) {    //스플릿 한 것중 첫번쨰 문자열은 안쓰는걸로
                printWriter.println(e + "<br>");
            }
            isFirst = false;
        }

    }
}
