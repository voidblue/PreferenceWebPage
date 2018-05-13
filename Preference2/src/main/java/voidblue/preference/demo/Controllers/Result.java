package voidblue.preference.demo.Controllers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;

@RestController
public class Result extends HttpServlet {
    @PostMapping("/result")
    public String doPost(@RequestParam("other1") String other1, @RequestParam("other2") String other2,
                       @RequestParam("other3") String other3, @RequestParam("other4") String other4,
                       @RequestParam("selected") String selected){


        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("selected", selected);
            jsonObject.put("other1", other1);
            jsonObject.put("other2", other2);
            jsonObject.put("other3", other3);
            jsonObject.put("other4", other4);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        jsonArray.put(jsonObject);
        return jsonArray.toString();

    }
}
