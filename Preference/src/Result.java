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
            bufferedOutputStream.write(path.getBytes());
            bufferedOutputStream.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }



        try {
            printWriter = resp.getWriter();
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
        String numOfCompanion = req.getParameter("numOfCompanion");
        String stayDuration = req.getParameter("stayDuration");
        String visitTime = req.getParameter("visitTime");
        String reason1 = req.getParameter("considerReason1");
        String reason2 = req.getParameter("considerReason2");
        String transportaion = req.getParameter("transportation");
        String typeOfCompanion = req.getParameter("companion");
        String accomodation = req.getParameter("accomodation");
        String typeOftrip = req.getParameter("tripType");
        String howGetInfo = req.getParameter("infoGet");
        String mainDestination = req.getParameter("primeReason");

//        String residence = req.getParameter("큐텔에서준비해줄것");
//        String gender = req.getParameter("큐텔에서준비해줄것");
//        String education = req.getParameter("큐텔에서준비해줄것");
//        String birthYear = req.getParameter("큐텔에서준비해줄것");

        String residence = "1";
        String gender = "1";
        String education = "1";
        String birthYear = "1";

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
        for (int i = 1 ;  i <= 6 ; i++){
            if ( i == iAccomodation ){
                AccomodationBuilder.append(i + " ");
            }
            else{
                AccomodationBuilder.append("0 ");
            }
        }
        String codeAccomodation = AccomodationBuilder.toString();

        String input = visitTime + " " + stayDuration + " " + mainDestination + " " + reason1 + " " +
                reason2+ " " + howGetInfo + " " + codeTypeOfCompanion + " 0 " + numOfCompanion + " " +
                codeAccomodation + " " + transportaion + " " + typeOftrip + " " + residence + " " + gender + " " + education + " " +
                birthYear + " " + currentMonth;
        //일단 미성년자 0으로 두기

        return input;
    }

}

