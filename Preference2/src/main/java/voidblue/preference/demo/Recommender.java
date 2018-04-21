package voidblue.preference.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
@Controller
public class Recommender extends HttpServlet {

    @PostMapping("/recommender")
    public String recommend(Model model, @RequestParam("numOfCompanion") String numOfCompanion,
                        @RequestParam("stayDuration") String stayDuration, @RequestParam("visitTime") String visitTime,
                        @RequestParam("considerReason1") String reason1, @RequestParam("considerReason2") String reason2,
                        @RequestParam("transportation")String transportation, @RequestParam("companion")String typeOfCompanion,
                        @RequestParam("accomodation")String accomodation, @RequestParam("tripType")String tripType,
                        @RequestParam("infoGet")String howGetInfo, @RequestParam("primeReason") String mainDestination,
                        @RequestParam("id")String id){
        //임시변수
        String residence = "1";


        int currentMonth = Calendar.MONTH;
        int iTypeOfCompanion = Integer.parseInt(typeOfCompanion);
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

        String input = null;
        HashMap userDataList = null;
        UserData userData = new UserData();
        try {
            userDataList = userData.getData(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (userDataList != null) {
            input = visitTime + " " + stayDuration + " " + mainDestination + " " + reason1 + " " +
                    reason2+ " " + howGetInfo + " " + codeTypeOfCompanion + " " + numOfCompanion + " "+ "0 " +
                    codeAccomodation + " " + transportation + " " + tripType + " " + residence + " " + userDataList.get("sex") + " " + userDataList.get("education") + " " +
                    userDataList.get("birth") + " "  + userDataList.get("job") + " " +  currentMonth;
        }
        input = "# \n" + input;


        String path = System.getProperty("user.dir");
        path += "/out/production/classes/voidblue/prefernce/demo";
        path += "/build";




        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path + "/input");
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            bufferedOutputStream.write(input.getBytes());
            bufferedOutputStream.close();
        } catch (IOException e) {
        }



        RecommendRunner recommend = RecommendRunner.getTempExcutedIntance();
        String[] result = recommend.getResult().split("ESC");
        ArrayList<String> sightSeeingSpots = new ArrayList();
        for (int i = 1; i < result.length ; i++){
            if (i % 2 == 1) {
                sightSeeingSpots.add(result[i]);
            }

        }
        return "recommender";
    }


    private String getParam(HttpServletRequest req, String s){
    if (req.getParameter(s).equals("guide")){
        return "0";
            } else if(req.getParameter(s) == null){
                return "0";
            }
        else return req.getParameter(s);
    }
}