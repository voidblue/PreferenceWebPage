import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Calendar;
import java.util.Date;

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

        String input = this.getInputText(req);
        input = "# \n" + input;


        String path = System.getProperty("user.dir");
        path += "/webapps/ROOT/WEB-INF/classes";
        path += "/build";

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path + "/input");
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            bufferedOutputStream.write(input.getBytes());
            bufferedOutputStream.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }



        try {
            printWriter = resp.getWriter();
            printWriter.println(path +"/input");
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

    private String getParam(HttpServletRequest req, String s){
    if (req.getParameter("s").equals("guide")){
        return "0";
            } else if(req.getParameter("s") == null){
                return "0";
            }
        else return req.getParameter(s);
    }




    private String getInputText(HttpServletRequest req){
        Filler filler = Filler.getInstance(req);

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

        String spssJob = filler.fillJob();
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
                typeOfCompanionBuilder.append(i).append(" ");
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
                AccomodationBuilder.append(i + " ");
            }
            else{
                AccomodationBuilder.append("0 ");
            }
        }
        String codeAccomodation = AccomodationBuilder.toString();

        String input = visitTime + " " + stayDuration + " " + mainDestination + " " + reason1 + " " +
                reason2+ " " + howGetInfo + " " + codeTypeOfCompanion + numOfCompanion + " "+ "0" +
                codeAccomodation + " " + transportation + " " + tripType + " " + residence + " " + gender + " " + education + " " +
                birthYear + " "  + job + " " +  currentMonth;

        return input;
    }
}