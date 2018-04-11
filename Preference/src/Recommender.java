import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Recommender extends HttpServlet {
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

        String input = this.getInputText(req);
        input = "# \n" + input;


        String path = System.getProperty("user.dir");
        path += "/webapps/ROOT/WEB-INF/classes";
        path += "/build";


        try {
            printWriter = resp.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path + "/input");
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            bufferedOutputStream.write(input.getBytes());
            bufferedOutputStream.close();
        } catch (java.io.IOException e) {
            printWriter.println(e.getMessage());
        }



        RecommendRunner recommend = RecommendRunner.getTempExcutedIntance();
        String[] result = recommend.getResult().split("ESC");
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        for (int i = 1; i < result.length ; i++){
            if (i % 2 == 1) {
                String str = (i/2 + 1) + "순위 여행지";
                try {
                    jsonObject.put(str ,result[i]);
                } catch (JSONException e) {
                    printWriter.println(e.getMessage());
                }
            }
            else {
                try {
                    jsonObject.put("좌표", result[i]);
                } catch (JSONException e) {
                    printWriter.println(e.getMessage());
                }
                jsonArray.put(jsonObject);
                jsonObject = new JSONObject();

            }
        }
        boolean isFirst = true;
        printWriter.println(jsonArray.toString() + "<br>");
        for (String e : result) {
            if (!isFirst) {    //스플릿 한 것중 첫번쨰 문자열은 안쓰는걸로
                printWriter.println(e + "<br>");
            }
            isFirst = false;
        }

    }


    private String getParam(HttpServletRequest req, String s){
    if (req.getParameter(s).equals("guide")){
        return "0";
            } else if(req.getParameter(s) == null){
                return "0";
            }
        else return req.getParameter(s);
    }




    private String getInputText(HttpServletRequest req){
        Filler filler = new Filler(req);

        // TODO: 큐텔에서 파라미터 준비해주는 대로 수정 필요
        String residence = "1";
        String gender = "1";
        String education = "1";
        String birthYear = "1994";
        String job = "1";


        String numOfCompanion = getParam(req,"numOfCompanion");
        String stayDuration = getParam(req,"stayDuration");
        String visitTime = getParam(req,"visitTime");
        String reason1 = getParam(req,"considerReason1");
        String reason2 = getParam(req, "considerReason2");
        String transportation = getParam(req, "transportation");
        String typeOfCompanion = getParam(req, "companion");
        String accomodation = getParam(req, "accomodation");
        String tripType = getParam(req,"tripType");
        String howGetInfo = getParam(req,"infoGet");
        String mainDestination = getParam(req, "primeReason");

        String id = req.getParameter("id");
        UserData userData = new UserData();
        try {
            userData.getConnect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            userData.getData(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        HashMap userDataList = null;
        try {
            userDataList = userData.getData(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String spssNumOfCompanion = filler.fillNumOfCompanion();
        String spssTypeOfCompanion = filler.fillTypeOfCompanion();
        String spssHowGetInfo = filler.fillHowGetInfo();
        String spssstayDuration = filler.fillStayDuration();
        String spssvisitTime = filler.fillVisitTime();
        String spssminorPresence = filler.fillMinorPresence();
        String spsstransportation = filler.fillTransportation();
        String spssreason1 = filler.fillReason1();
        String spssreason2 = filler.fillReason2();
        String spssmainDestination = filler.fillMainDest();
        String spsstripType = filler.fillTripType();
        String spssaccomodation = filler.fillAccomodation();

        int currentMonth = Calendar.MONTH;



        int iTypeOfCompanion = Integer.parseInt(typeOfCompanion.toString());
        StringBuilder typeOfCompanionBuilder = new StringBuilder();
        for (int i = 1 ; i <= 7 ; i++){
            if (i == iTypeOfCompanion){
                typeOfCompanionBuilder.append("1 ");
            }
            else{
                typeOfCompanionBuilder.append("0 ");
            }
        }
        String codeTypeOfCompanion = typeOfCompanionBuilder.toString();

        int iAccomodation = Integer.parseInt(accomodation);
        StringBuilder AccomodationBuilder = new StringBuilder();
        for (int i = 1 ;  i <= 8 ; i++){
            if ( i == iAccomodation ){
                AccomodationBuilder.append("1 ");
            }
            else{
                AccomodationBuilder.append("0 ");
            }
        }
        String codeAccomodation = AccomodationBuilder.toString();

        String input = visitTime + " " + stayDuration + " " + mainDestination + " " + reason1 + " " +
                reason2+ " " + howGetInfo + " " + codeTypeOfCompanion + " " + numOfCompanion + " "+ "0 " +
                codeAccomodation + " " + transportation + " " + tripType + " " + residence + " " + userDataList.get("sex") + " " + userDataList.get("education") + " " +
                userDataList.get("birth") + " "  + userDataList.get("job") + " " +  currentMonth;

        return input;
    }
}