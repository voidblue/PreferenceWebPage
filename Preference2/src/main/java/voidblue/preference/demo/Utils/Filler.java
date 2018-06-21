package voidblue.preference.demo.Utils;

import voidblue.preference.demo.Models.PollData;
import voidblue.preference.demo.Models.User;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Filler {
    private User user;
    private PollData pollData;

    public PollData fillPollData(User user, PollData pollData) {
        this.user = user;
        this.pollData = pollData;

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

        return this.pollData;
    }

    private boolean isEmpty(String str) {
        boolean isEmpty = false;

        if (str.equals("null") || str == null) { isEmpty = true; }

        return isEmpty;
    }

    private String yearToAge(String year)
    {
        Calendar calendar = new GregorianCalendar(Locale.KOREA);

        int birthYear = Integer.parseInt(year);
        int currentYear = calendar.get(Calendar.YEAR);
        int age = currentYear - birthYear + 1;
        String result = null;

        if (age >= 0 && age < 20) { result = "10대"; }
        else if (age >= 20 && age < 30) { result = "20대"; }
        else if (age >= 30 && age < 40) { result = "30대"; }
        else if (age >= 40 && age < 50) { result = "40대"; }
        else if (age >= 50 && age < 60) { result = "50대"; }
        else if (age >= 60 && age < 70) { result = "60대"; }
        else if (age >= 70) { result = "70대"; }

        return result;
    }

    private String fillNumOfCompanion()
    {
        String numOfCompanion = pollData.getCompanion();

        if (isEmpty(numOfCompanion))
        {
            String age = yearToAge(user.getBirth());
            String job = user.getJob();
            if (age.equals("30대") && job.equals("12")) { numOfCompanion = "3"; }
            else if (age.equals("40대")) { numOfCompanion = "3"; }
            else { numOfCompanion = "2"; }
        }

        return numOfCompanion;
    }

    private String fillTypeOfCompanion()
    {
        String typeOfCompanion = pollData.getCompanion();

        if (isEmpty(typeOfCompanion))
        {
            String education = user.getEducation();
            String age = yearToAge(user.getBirth());
            String job = user.getJob();

            if (age.equals("10대") && education.equals("2")) { typeOfCompanion = "5"; }
            else if (age.equals("20대")) {
                if (job.equals("11") || job.equals("5") || job.equals("8")
                        || job.equals("13") || job.equals("4")) {
                    typeOfCompanion = "5";
                } else { typeOfCompanion = "2"; }
            } else { typeOfCompanion = "3"; }
        }

        return typeOfCompanion;
    }

    private String fillHowGetInfo()
    {
        String howGetInfo = pollData.getInfoGet();

        if (isEmpty(howGetInfo))
        {
            String age = yearToAge(user.getBirth());

            if (age.equals("60대") || age.equals("70대")) { howGetInfo = "2"; }
            else { howGetInfo = "3"; }
        }

        return howGetInfo;
    }

    private String fillStayDuration()
    {
        String stayDuration = pollData.getStayDuration();

        if (isEmpty(stayDuration))
        {
            int currentMonth = Calendar.MONTH;

            if(currentMonth == 7 || currentMonth == 8) { stayDuration = "4"; }
            else { stayDuration = "3"; }
        }

        return stayDuration;
    }

    private String fillVisitTime()
    {
        String visitTime = pollData.getVisitTime();

        if (isEmpty(visitTime))
        {
            String age = yearToAge(user.getBirth());
            String education = user.getEducation();
            int currentMonth = Calendar.MONTH;

            if (age.equals("30대")) {
                if (currentMonth == 1 || currentMonth == 2) { visitTime = "1"; }
            } else if (education.equals("2") || education.equals("1")) { visitTime = "1"; }
            else { visitTime = "2"; }
        }

        return visitTime;
    }

    private String fillTransportation()
    {
        String transportaion = pollData.getTransportation();

        if (isEmpty(transportaion)) {
            String numOfCompanion = fillNumOfCompanion();

            if (numOfCompanion.equals("1")) { transportaion = "3"; }
            else if (numOfCompanion.equals("10")) { transportaion = "4"; }
            else { numOfCompanion = "1"; }
        }

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

        return reason2;
    }

    private String fillPrimeReason()
    {
        String primeReason = pollData.getPrimeReason();

        if (isEmpty(primeReason))
        {
            primeReason = fillReason1();
        }

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
            String age = yearToAge(user.getBirth());


            if (typeOfCompanion.equals("5") || typeOfCompanion.equals("1")) {
                if (age.equals("20대") && stayDuration.equals("4")) { accomodation = "3"; }
                else if (age.equals("10대") || age.equals("30대")) {
                    if (transportaion.equals("3") || transportaion.equals("6")
                            || transportaion.equals("5")) {
                        accomodation = "3";
                    }
                }
            } else { accomodation = "1"; }
        }

        return accomodation;
    }
}
