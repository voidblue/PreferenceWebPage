package voidblue.preference.demo.View;

import lombok.Cleanup;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import voidblue.preference.demo.Dao.UserDao;
import voidblue.preference.demo.Dao.User;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
@Controller
public class Recommender {

    @PostMapping("/recommender")
    public String recommend(Model model, @RequestParam("numOfCompanion") String numOfCompanion,
                        @RequestParam("stayDuration") String stayDuration, @RequestParam("visitTime") String visitTime,
                        @RequestParam("considerReason1") String reason1, @RequestParam("considerReason2") String reason2,
                        @RequestParam("transportation")String transportation, @RequestParam("companion")String typeOfCompanion,
                        @RequestParam("accomodation")String accomodation, @RequestParam("tripType")String tripType,
                        @RequestParam("infoGet")String howGetInfo, @RequestParam("primeReason") String mainDestination,
                        @RequestParam("id")String id){

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
        User user = null;
        UserDao userDao = new UserDao();
        try {
            user = userDao.getUser(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        input = visitTime + " " + stayDuration + " " + mainDestination + " " + reason1 + " " +
                reason2+ " " + howGetInfo + " " + codeTypeOfCompanion + " " + numOfCompanion + " "+ "0 " +
                codeAccomodation + " " + transportation + " " + tripType + " " + residence + " " + user.getSex() + " " + user.getEducation() + " " +
                user.getBirth() + " "  + user.getJob() + " " +  currentMonth;

        input = "# \n" + input;


        String path = System.getProperty("user.dir");
        path += "/out/production/classes/voidblue/prefernce/demo";
        path += "/build";




        try {
            @Cleanup
            FileOutputStream fileOutputStream = new FileOutputStream(path + "/input");
            @Cleanup
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            bufferedOutputStream.write(input.getBytes());
            bufferedOutputStream.close();
        } catch (IOException e) {
        }


        String[] result = new String[0];
        try {
            result = excuteRecomender().split("ESC");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<SightSeeingSpot> sightSeeingSpots = new ArrayList();
        int index = 0;
        for (int i = 1; i < result.length ; i++){
            if (i % 2 == 1) {
                SightSeeingSpot sightSeeingSpot = new SightSeeingSpot();
                sightSeeingSpot.setName(result[i]);
                sightSeeingSpot.setRank(index);
                index++;
                sightSeeingSpots.add(sightSeeingSpot);
            }

        }
        model.addAttribute("sightSeeingSpots",sightSeeingSpots);

        return "recommender";
    }




    private String excuteRecomender() throws IOException {
        String path = System.getProperty("user.dir");
        path += "/out/production/classes/voidblue/preference/demo";
//        path += "/out/production/Preference";
        Process p = null;
        try{
            p = Runtime.getRuntime().exec(path + "/build/exe.linux-x86_64-3.5/recommend");
            p.waitFor();
        } catch (IOException ex){
            System.out.println(ex.getMessage());} catch (InterruptedException e) {
            e.printStackTrace();
        }
        StringBuffer sb = new StringBuffer();
        @Cleanup
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));

        String str;
        while((str=br.readLine())!=null){
            sb.append(str);
        }
        return sb.toString();
    }



}

@Data
class SightSeeingSpot {
    private int rank;
    private String name;
}
