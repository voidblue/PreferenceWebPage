import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

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

    public String fillNumOfCompanion()
    {
        String numOfCompanion = req.getParameter("numOfCompanion");

        if (numOfCompanion.equals("guide"))
        {
            // TODO: 큐텔에서 파라미터를 제공해 주는 대로 그에 맞춰 바꿔야 함 (연령, 직업)
            String age = req.getParameter("연령");
            String job = req.getParameter("직업");
            if (age.equals("30대") && job.equals("주부")) { numOfCompanion = "3~4명"; }
            else if (age.equals("40대")) { numOfCompanion = "3~4명"; }
            else { numOfCompanion = "2명"; }
        }

        return numOfCompanion;
    }

    public String fillTypeOfCompanion()
    {
        String typeOfCompanion = req.getParameter("동료");

        if (typeOfCompanion.equals("guide"))
        {
            // TODO: 큐텔에서 파라미터를 제공해 주는 대로 그에 맞춰 바꿔야 함 (연령, 직업, 학력)
            String age = req.getParameter("연령");
            String education = req.getParameter("학력");
            String job = req.getParameter("직업");

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

        if (howGetInfo.equals("guide"))
        {
            // TODO: 큐텔에서 파라미터를 제공해 주는 대로 그에 맞춰 바꿔야 함
            String age = req.getParameter("연령");

            if (age.equals("60대") || age.equals("70대")) { howGetInfo = "친구/친지/동료"; }
            else { howGetInfo = "인터넷 정보검색"; }
        }

        return howGetInfo;
    }

    public String fillStayDuration()
    {
        String stayDuration = req.getParameter("stayDuration");

        if (stayDuration.equals("guide"))
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

        if (visitTime.equals("guide"))
        {
            // TODO: 큐텔에서 파라미터를 제공해 주는 대로 그에 맞춰 바꿔야 함
            String age = req.getParameter("연령");
            String education = req.getParameter("학력");
            int currentMonth = Calendar.MONTH;

            if (age.equals("30대")) {
                if (currentMonth == 1 || currentMonth == 2) { visitTime = "처음"; }
            } else if (education.equals("대학생") || education.equals("고졸이하")) { visitTime = "처음"; }
            else { visitTime = "2~3회"; }
        }

        return visitTime;
    }

    public String fillMinor()
    {
        // TODO

        return "0";
    }

    public String fillTransportaion()
    {
        String transportaion = req.getParameter("교통수단");

        if (transportaion.equals("guide")) {
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

        if (reason1.equals("guide"))
        {
            String howGetInfo = fillHowGetInfo();
            String transportaion = fillTransportaion();

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

        if (reason2.equals("guide"))
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

        if (mainDestination.equals("guide"))
        {
            mainDestination = fillReason1();
        }

        return mainDestination;
    }

    public String fillTypeOfTrip()
    {
        String typeOftrip = req.getParameter("여행종류");

        if (typeOftrip.equals("guide")) {
            String transportation = fillTransportaion();
            String numOfCompanion = fillNumOfCompanion();

            if (transportation.equals("전세버스")) { typeOftrip = "패키지여행"; }
            else if (transportation.equals("대절택시") || transportation.equals("시티투어버스")) {
                if (numOfCompanion.equals("5~9명") || numOfCompanion.equals("10명 이상")) { typeOftrip = "패키지여행"; }
            } else { typeOftrip = "개별여행"; }
        }

        return typeOftrip;
    }

}
