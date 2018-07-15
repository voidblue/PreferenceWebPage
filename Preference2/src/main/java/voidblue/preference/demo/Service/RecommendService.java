package voidblue.preference.demo.Service;

import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import voidblue.preference.demo.Models.*;
import voidblue.preference.demo.Utils.Filler;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
@Service
public class RecommendService  {
    @Autowired
    UserDao userDao;
    @Autowired
    InputDao inputDao;

    public ArrayList<SightSeeingSpot> getSightSeeingSpots(PollData pollData) {
        String argv = getArgvsForPythonTensorflow(pollData);
        String[] result = new String[0];
        try {
            result = excutePythonRecomender(argv);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<SightSeeingSpot> sightSeeingSpots = new ArrayList();
        for (int i = 1; i < result.length ; i++){
            SightSeeingSpot sightSeeingSpot = new SightSeeingSpot();
            sightSeeingSpot.setName(result[i]);
            sightSeeingSpot.setRank(i);
            sightSeeingSpots.add(sightSeeingSpot);

        }
        System.out.println(sightSeeingSpots.get(0).toString());
        return sightSeeingSpots;
    }

    private String getArgvsForPythonTensorflow(PollData pollData) {
        User user = userDao.getUser(pollData.getId());
        Filler filler = new Filler();
        pollData = filler.fillPollData(user, pollData);

        String currentMonth = Integer.toString(Calendar.MONTH);


        int iTypeOfCompanion = Integer.parseInt(pollData.getCompanion());
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

        int iAccomodation = Integer.parseInt(pollData.getAccomodation());
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

//        1 1 2 3 0 0 1 0 0 0 0  2 0 1 0 0 0 0 0 0 0  1 1 1 2 2 2018 8 2
//        1 2 1 3 4 2 0 1 0 0 0 0 0  2 0 0 0 0 1 0 0 0 0  2 1 1 2 2 2018 8 2
        String dataForInput = pollData.getVisitTime() + " " + pollData.getStayDuration() + " " + pollData.getPrimeReason()
                + " " + pollData.getConsiderReason1() + " " + pollData.getConsiderReason2() + " " +
                pollData.getInfoGet() + " " + codeTypeOfCompanion + " " + pollData.getNumOfCompanion() + " "+ "0 " +
                codeAccomodation + " " + pollData.getTransportation() + " " + pollData.getTripType() + " " + user.getRegion()
                + " " + user.getSex() + " " + user.getEducation() + " " +
                user.getBirth() + " "  + user.getJob() + " " +  currentMonth;



        return dataForInput;
    }


    private String[] excutePythonRecomender(String argv) throws IOException {
        String path = System.getProperty("user.dir");
        path += "/src/main/java/voidblue/preference/demo";
        Process p = null;
        try{
            p = Runtime.getRuntime().exec("python3 "+ path + "/Preference/PBRS.py -r "+ argv);
            System.out.println("python3 "+ path + "/Preference/PBRS.py -r "+ argv);
            p.waitFor();
        } catch (IOException ex){
            System.out.println(ex.getMessage());} catch (InterruptedException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();

        @Cleanup
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));

        String str;
        while((str=br.readLine())!=null){
            sb.append(str);
        }

        System.out.println(sb.toString());
        String[] result = sb.toString().split("ESC");
        System.out.println(result.length);
        return result;
    }
}
