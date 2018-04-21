package voidblue.preference.demo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Result extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            resp.sendError(404);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {


        PrintWriter printWriter = resp.getWriter();
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("selected", req.getParameter("selected"));
            jsonObject.put("other1", req.getParameter("other1"));
            jsonObject.put("other2", req.getParameter("other2"));
            jsonObject.put("other3", req.getParameter("other3"));
            jsonObject.put("other4", req.getParameter("other4"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        jsonArray.put(jsonObject);
        printWriter.print(jsonArray.toString());

    }
}
