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

    private String getInputText(HttpServletRequest req){
        Filler filler = Filler.getInstance(req);

        // TODO: 큐텔에서 파라미터 준비해주는 대로 수정 필요
        String residence = "1";
        String gender = "1";
        String education = "1";
        String birthYear = "1994";

        String job = filler.fillJob();
        String numOfCompanion = filler.fillNumOfCompanion();
        String typeOfCompanion = filler.fillTypeOfCompanion();
        String howGetInfo = filler.fillHowGetInfo();
        String stayDuration = filler.fillStayDuration();
        String visitTime = filler.fillVisitTime();
        String minorPresence = filler.fillMinorPresence();
        String transportation = filler.fillTransportation();
        String reason1 = filler.fillReason1();
        String reason2 = filler.fillReason2();
        String mainDestination = filler.fillMainDest();
        String tripType = filler.fillTripType();
        String accomodation = filler.fillAccomodation();

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
                reason2+ " " + howGetInfo + " " + codeTypeOfCompanion + numOfCompanion + " "+ minorPresence +
                codeAccomodation + " " + transportation + " " + tripType + " " + residence + " " + gender + " " + education + " " +
                birthYear + " "  + job + " " +  currentMonth;

        return input;
    }



}

