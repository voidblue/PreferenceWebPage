package voidblue.preference.demo.Service;

import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import voidblue.preference.demo.Models.*;

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
        saveInputForPythonTensorFlow(pollData);
        String[] result = new String[0];
        try {
            result = excutePythonRecomender();
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
        return sightSeeingSpots;
    }

    private void saveInputForPythonTensorFlow(PollData pollData) {
        String residence = "1";

        String currentMonth = Integer.toString(Calendar.MONTH);

        Input input = new Input();
        input.setVisitTime(pollData.getVisitTime());
        input.setStayDuration(pollData.getStayDuration());
        input.setMainDestination(pollData.getPrimeReason());
        input.setConsiderReason1(pollData.getConsiderReason1());
        input.setConsiderReason2(pollData.getConsiderReason2());
        input.setHowGetInfomation(pollData.getInfoGet());
        input.setTypeOfCompanion(pollData.getCompanion());
        input.setNumOfPeople(pollData.getNumOfCompanion());
        input.setNumOfMinor("0");
        input.setAccomodation(pollData.getAccomodation());
        input.setTransportaion(pollData.getTransportation());
        input.setTripType(pollData.getTripType());
        input.setMonth(currentMonth);
        input.setUserId(pollData.getId());
        inputDao.tryInsertThenUpdate(input);



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


        User user = userDao.getUser(pollData.getId());

        String dataForInput = pollData.getVisitTime() + " " + pollData.getStayDuration() + " " + pollData.getPrimeReason()
                + " " + pollData.getConsiderReason1() + " " + pollData.getConsiderReason2() + " " +
                pollData.getInfoGet() + " " + codeTypeOfCompanion + " " + pollData.getNumOfCompanion() + " "+ "0 " +
                codeAccomodation + " " + pollData.getTransportation() + " " + pollData.getTripType() + " " + residence
                + " " + user.getSex() + " " + user.getEducation() + " " +
                user.getBirth() + " "  + user.getJob() + " " +  currentMonth;

        dataForInput = "# \n" + dataForInput;


        String path = System.getProperty("user.dir");
        path += "/out/production/classes/voidblue/prefernce/demo";
        path += "/build";


        try {
            @Cleanup
            FileOutputStream fileOutputStream = new FileOutputStream(path + "/input");
            @Cleanup
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            bufferedOutputStream.write(dataForInput.getBytes());
            bufferedOutputStream.close();
        } catch (IOException e) {
        }

    }


    private String[] excutePythonRecomender() throws IOException {
        String path = System.getProperty("user.dir");
        path += "/src/main/java/voidblue/preference/demo";
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


        String[] result = sb.toString().split("ESC");

        return result;
    }
}
