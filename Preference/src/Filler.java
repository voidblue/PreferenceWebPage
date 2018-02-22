import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Filler {
    private HttpServletRequest req;

    private Filler(HttpServletRequest req)
    {
        this.req = req;
    }

    public static Filler getInstance(HttpServletRequest req)
    {
        return new Filler(req);
    }

    private boolean isEmpty(String str) {
        boolean isEmpty = false;

        if (str.equals("guide") || str == null) { isEmpty = true; }

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

    public String fillJob()
    {
        String job = req.getParameter("job");

        if (isEmpty(job))
        {
            job = "전문가 및 관련종사자";
        }

        return job;
    }

    public String fillNumOfCompanion()
    {
        String numOfCompanion = req.getParameter("numOfCompanion");

        if (isEmpty(numOfCompanion))
        {
            // TODO: 큐텔에서 파라미터를 제공해 주는 대로 그에 맞춰 바꿔야 함
//            String age = req.getParameter("연령");
            String age = yearToAge("1994");
            String job = fillJob();
            if (age.equals("30대") && job.equals("주부")) { numOfCompanion = "3~4명"; }
            else if (age.equals("40대")) { numOfCompanion = "3~4명"; }
            else { numOfCompanion = "2명"; }
        }

        return numOfCompanion;
    }

    public String fillTypeOfCompanion()
    {
        String typeOfCompanion = req.getParameter("동료");

        if (isEmpty(typeOfCompanion))
        {
            // TODO: 큐텔에서 파라미터를 제공해 주는 대로 그에 맞춰 바꿔야 함
//            String age = req.getParameter("연령");
            String age = yearToAge("1994");
//            String education = req.getParameter("학력");
            String education = "1";
            String job = fillJob();

            if (age.equals("10대") && education.equals("대학생")) { typeOfCompanion = "친구"; }
            else if (age.equals("20대")) {
                if (job.equals("학생") || job.equals("판매종사자") || job.equals("장치기계조작 및 조립종사자")
                        || job.equals("무직") || job.equals("서비스종사자")) {
                    typeOfCompanion = "친구";
                } else { typeOfCompanion = "부부/연인"; }
            } else { typeOfCompanion = "가족/친척"; }
        }

        return typeOfCompanion;
    }

    public String fillHowGetInfo()
    {
        String howGetInfo = req.getParameter("정보습득방법");

        if (isEmpty(howGetInfo))
        {
            // TODO: 큐텔에서 파라미터를 제공해 주는 대로 그에 맞춰 바꿔야 함
//            String age = req.getParameter("연령");
            String age = yearToAge("1994");

            if (age.equals("60대") || age.equals("70대")) { howGetInfo = "친구/친지/동료"; }
            else { howGetInfo = "인터넷 정보검색"; }
        }

        return howGetInfo;
    }

    public String fillStayDuration()
    {
        String stayDuration = req.getParameter("stayDuration");

        if (isEmpty(stayDuration))
        {
            int currentMonth = Calendar.MONTH;

            if(currentMonth == 7 || currentMonth == 8) { stayDuration = "4~5일"; }
            else { stayDuration = "2~3일"; }
        }

        return stayDuration;
    }

    public String fillVisitTime()
    {
        String visitTime = req.getParameter("visitTime");

        if (isEmpty(visitTime))
        {
            // TODO: 큐텔에서 파라미터를 제공해 주는 대로 그에 맞춰 바꿔야 함
//            String age = req.getParameter("연령");
//            String education = req.getParameter("학력");
            String age = yearToAge("1994");
            String education = "1";
            int currentMonth = Calendar.MONTH;

            if (age.equals("30대")) {
                if (currentMonth == 1 || currentMonth == 2) { visitTime = "처음"; }
            } else if (education.equals("대학생") || education.equals("고졸이하")) { visitTime = "처음"; }
            else { visitTime = "2~3회"; }
        }

        return visitTime;
    }

    public String fillMinorPresence()
    {
        // TODO : 파라미터 이름 확인 및 수정 필요
        String minorPresence = req.getParameter("만15세미만동반유무");

        if (isEmpty(minorPresence))
        {
            String typeOfCompanion = fillTypeOfCompanion();
//            String age = req.getParameter("연령");
            String age = yearToAge("1994");

            if (typeOfCompanion.equals("가족/친척")) {
                if (age.equals("30대") || age.equals("40대") || age.equals("70대")) { minorPresence = "1";}
            } else { minorPresence = "0"; }
        }

        return minorPresence;
    }

    public String fillTransportation()
    {
        String transportaion = req.getParameter("교통수단");

        if (isEmpty(transportaion)) {
            String numOfCompanion = fillNumOfCompanion();

            if (numOfCompanion.equals("혼자")) { transportaion = "대중교통"; }
            else if (numOfCompanion.equals("10명 이상")) { transportaion = "전세버스"; }
            else { numOfCompanion = "렌터카"; }
        }

        return transportaion;
    }

    public String fillReason1()
    {
        String reason1 = req.getParameter("고려요인1");

        if (isEmpty(reason1))
        {
            String howGetInfo = fillHowGetInfo();
            String transportaion = fillTransportation();

            if (howGetInfo.equals("과거여행경험") || howGetInfo.equals("신문/잡지/서적")
                    || howGetInfo.equals("TV/라디오")) {
                if (transportaion.equals("자전거/라디오") || transportaion.equals("대절택시")
                        || transportaion.equals("시티투어버스"))
                {
                    reason1 = "휴양/휴식";
                }
            } else { reason1 = "자연경관감상"; }
        }

        return reason1;
    }

    public String fillReason2()
    {
        String reason2 = req.getParameter("고려요인2");

        if (isEmpty(reason2))
        {
            String reason1 = fillReason1();

            if (reason1.equals("자연경관감상") || reason1.equals("가까운거리")) { reason2 = "휴양/휴식"; }
            else if (reason1.equals("여행비용") || reason1.equals("기후/사계절") || reason1.equals("안전/치안")
                    || reason1.equals("한류관광지방문")) { reason2 = "가까운거리"; }
            else { reason2 = "음식/미식탐방"; }
        }

        return reason2;
    }

    public String fillMainDest()
    {
        String mainDestination = req.getParameter("주목적");

        if (isEmpty(mainDestination))
        {
            mainDestination = fillReason1();
        }

        return mainDestination;
    }

    public String fillTripType()
    {
        String typeOftrip = req.getParameter("여행종류");

        if (isEmpty(typeOftrip)) {
            String transportation = fillTransportation();
            String numOfCompanion = fillNumOfCompanion();

            if (transportation.equals("전세버스")) { typeOftrip = "패키지여행"; }
            else if (transportation.equals("대절택시") || transportation.equals("시티투어버스")) {
                if (numOfCompanion.equals("5~9명") || numOfCompanion.equals("10명 이상")) { typeOftrip = "패키지여행"; }
            } else { typeOftrip = "개별여행"; }
        }

        return typeOftrip;
    }

    public String fillAccomodation()
    {
        String accomodation = req.getParameter("숙박");

        if (isEmpty(accomodation))
        {
            // TODO: 큐텔에서 파라미터를 제공해 주는 대로 그에 맞춰 바꿔야 함
            String transportaion = fillTransportation();
            String typeOfCompanion = fillTypeOfCompanion();
            String stayDuration = fillStayDuration();
//            String age = req.getParameter("연령");
            String age = yearToAge("1994");


            if (typeOfCompanion.equals("친구") || typeOfCompanion.equals("혼자왔음")) {
                if (age.equals("20대") && stayDuration.equals("4~5일")) { accomodation = "게스트하우스"; }
                else if (age.equals("10대") || age.equals("30대")) {
                    if (transportaion.equals("대중교통") || transportaion.equals("자전거/오토바이")
                            || transportaion.equals("시티투어버스")) {
                        accomodation = "게스트하우스";
                    }
                }
            } else { accomodation = "호텔"; }
        }

        return accomodation;
    }
}
