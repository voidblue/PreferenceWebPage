package voidblue.preference.demo.Utils;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import voidblue.preference.demo.Models.PollData;
import voidblue.preference.demo.Models.User;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Filler {
    private User user;
    private PollData pollData;
    private Calendar calendar;
    private int rangeOfRange;

    public PollData fillPollData(User user, PollData pollData) {
        this.user = user;
        this.pollData = pollData;
        calendar = Calendar.getInstance();
        pollData.setNumOfCompanion(fillNumOfCompanion());
        pollData.setCompanion(fillTypeOfCompanion());
        pollData.setInfoGet(fillHowGetInfo());
        pollData.setStayDuration(fillStayDuration());
        pollData.setVisitTime(fillVisitTime());
        pollData.setTransportation(fillTransportation());
        pollData.setConsiderReason1(fillReason1());
        pollData.setConsiderReason2(fillReason2());
        pollData.setPrimeReason(fillPrimeReason());
        pollData.setTripType(fillTripType());
        pollData.setAccomodation(fillAccomodation());

        rangeOfRange = getRangeOfAge();
        return this.pollData;
    }

    private boolean isEmpty(String str) {
        boolean isEmpty = false;

        if (str.equals("null") || str.equals("")) { isEmpty = true; }

        return isEmpty;
    }



    private String fillNumOfCompanion()
    {
        String numOfCompanion = pollData.getCompanion();

        if (isEmpty(numOfCompanion))
        {
            String job = user.getJob();
            if (this.rangeOfRange == 3 && job.equals("12")) { numOfCompanion = "3"; }
            else if (this.rangeOfRange == 4) { numOfCompanion = "3"; }
            else { numOfCompanion = "2"; }
        }
//        System.out.println("동료수" +  numOfCompanion);
        return numOfCompanion;
    }

    private String fillTypeOfCompanion()
    {
        String typeOfCompanion = pollData.getCompanion();

        if (isEmpty(typeOfCompanion))
        {
            String education = user.getEducation();
            String job = user.getJob();

            if (this.rangeOfRange == 1 && education.equals("2")) { typeOfCompanion = "5"; }
            else if (this.rangeOfRange == 2) {
                if (job.equals("11") || job.equals("5") || job.equals("8")
                        || job.equals("13") || job.equals("4")) {
                    typeOfCompanion = "5";
                } else { typeOfCompanion = "2"; }
            } else { typeOfCompanion = "3"; }
        }

//        System.out.println("동료타입" +  typeOfCompanion);
        return typeOfCompanion;
    }

    private String fillHowGetInfo()
    {
        String howGetInfo = pollData.getInfoGet();

        if (isEmpty(howGetInfo))
        {

            if (this.rangeOfRange == 6 || this.rangeOfRange == 7) { howGetInfo = "2"; }
            else { howGetInfo = "3"; }
        }

//        System.out.println("정보" +  howGetInfo);
        return howGetInfo;
    }

    private String fillStayDuration()
    {
        String stayDuration = pollData.getStayDuration();

        if (isEmpty(stayDuration))
        {
            int currentMonth = calendar.get(Calendar.MONTH);

            if(currentMonth == 7 || currentMonth == 8) { stayDuration = "4"; }
            else { stayDuration = "3"; }
        }
//        System.out.println("방문기간" +  stayDuration);
        return stayDuration;
    }

    private String fillVisitTime()
    {
        String visitTime = pollData.getVisitTime();

        if (isEmpty(visitTime))
        {
            String education = user.getEducation();
            int currentMonth = calendar.get(Calendar.MONTH);

            if (this.rangeOfRange == 3) {
                if (currentMonth == 1 || currentMonth == 2) { visitTime = "1"; }
            } else if (education.equals("2") || education.equals("1")) { visitTime = "1"; }
            else { visitTime = "2"; }
        }
//        System.out.println("방문횟수" +  visitTime);
        return visitTime;
    }

    private String fillTransportation()
    {
        String transportaion = pollData.getTransportation();

        if (isEmpty(transportaion)) {
            String numOfCompanion = fillNumOfCompanion();

            if (numOfCompanion.equals("1")) { transportaion = "3"; }
            else if (numOfCompanion.equals("10")) { transportaion = "4"; }
            else { transportaion = "1"; }
        }
//        System.out.println("교통수단" + transportaion);
        return transportaion;
    }

    private String fillReason1()
    {
        String reason1 = pollData.getConsiderReason1();

        if (isEmpty(reason1))
        {
            String howGetInfo = fillHowGetInfo();
            String transportaion = fillTransportation();

            if (howGetInfo.equals("8") || howGetInfo.equals("5")
                    || howGetInfo.equals("6")) {
                if (transportaion.equals("6") || transportaion.equals("2")
                        || transportaion.equals("5"))
                {
                    reason1 = "2";
                }
            } else { reason1 = "1"; }
        }

//        System.out.println("이유1" + reason1);
        return reason1;
    }

    private String fillReason2()
    {
        String reason2 = pollData.getConsiderReason2();

        if (isEmpty(reason2))
        {
            String reason1 = fillReason1();

            if (reason1.equals("1") || reason1.equals("8")) { reason2 = "2"; }
            else if (reason1.equals("7") || reason1.equals("6") || reason1.equals("9")
                    || reason1.equals("10")) { reason2 = "8"; }
            else { reason2 = "4"; }
        }

//        System.out.println("이유2" + reason2);
        return reason2;
    }

    private String fillPrimeReason()
    {
        String primeReason = pollData.getPrimeReason();

        if (isEmpty(primeReason))
        {
            primeReason = fillReason1();
        }

//        System.out.println("주목적" + primeReason);
        return primeReason;
    }

    private String fillTripType()
    {
        String typeOftrip = pollData.getTripType();

        if (isEmpty(typeOftrip)) {
            String transportation = fillTransportation();
            String numOfCompanion = fillNumOfCompanion();

            if (transportation.equals("4")) { typeOftrip = "2"; }
            else if (transportation.equals("2") || transportation.equals("5")) {
                if (numOfCompanion.equals("5") || numOfCompanion.equals("10")) { typeOftrip = "2"; }
            } else { typeOftrip = "1"; }
        }

//        System.out.println("여행타입" + typeOftrip);
        return typeOftrip;
    }

    private String fillAccomodation()
    {
        String accomodation = pollData.getAccomodation();

        if (isEmpty(accomodation))
        {
            String transportaion = fillTransportation();
            String typeOfCompanion = fillTypeOfCompanion();
            String stayDuration = fillStayDuration();


            if (typeOfCompanion.equals("5") || typeOfCompanion.equals("1")) {
                if (this.rangeOfRange == 2 && stayDuration.equals("4")) { accomodation = "3"; }
                else if (this.rangeOfRange == 2 || this.rangeOfRange == 3) {
                    if (transportaion.equals("3") || transportaion.equals("6")
                            || transportaion.equals("5")) {
                        accomodation = "3";
                    }
                }
            } else { accomodation = "1"; }
        }

//        System.out.println("숙박" + accomodation);
        return accomodation;
    }

    private int getRangeOfAge(){
        int birthYear = Integer.parseInt(user.getBirth());
        int thisYear;
        thisYear = calendar.get(Calendar.YEAR);
        int rangeOFYear;
        if(thisYear - birthYear < 10) rangeOFYear = 0;
        else if(thisYear - birthYear < 20) rangeOFYear = 1;
        else if(thisYear - birthYear < 30) rangeOFYear = 2;
        else if(thisYear - birthYear < 40) rangeOFYear = 3;
        else if(thisYear - birthYear < 50) rangeOFYear = 4;
        else if(thisYear - birthYear < 60) rangeOFYear = 5;
        else if(thisYear - birthYear < 70) rangeOFYear = 6;
        else rangeOFYear = 7;

        return rangeOFYear;
    }
}
