package voidblue.preference.demo.Models;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;

public class InputDao {
    private JdbcTemplate jdbcTemplate;
    public InputDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void tryInsertThenUpdate(Input input) {

        String isAlone = "0";
        String isCouple = "0";
        String isFamily = "0";
        String isCollgue = "0";
        String isFriend = "0";
        String isAmity = "0";
        String etcCompanion = "0";
        switch (input.getTypeOfCompanion()){
            case "1":
                isAlone = "1";
                break;
            case "2":
                isCouple = "1";
                break;
            case "3":
                isFamily = "1";
                break;
            case "4":
                isCollgue = "1";
                break;
            case "5":
                isFriend = "1";
                break;
            case "6":
                isAmity = "1";
                break;
            case "7":
                etcCompanion = "1";
                break;
        }

        String isHotel = "0";
        String isMotel = "0";
        String isGuestHouse = "0";
        String isPension = "0";
        String isResort = "0";
        String isFriendsHouse = "0";
        String noAccomodation = "0";
        String etcAccomodation = "0";

        switch (input.getAccomodation()){
            case "1":
                isHotel = "1";
                break;
            case "2":
                isMotel = "1";
                break;
            case "3":
                isGuestHouse = "1";
                break;
            case "4":
                isPension = "1";
                break;
            case "5":
                isResort = "1";
                break;
            case "6":
                isFriendsHouse = "1";
                break;
            case "7":
                noAccomodation = "1";
                break;
            case "8":
                etcAccomodation = "1";
                break;
        }

        String insertQuery = "INSERT INTO INPUTS(VISIT_TIME, STAY_DURATION, MAIN_DESTINATION, CONSIDER_REASON1, CONSIDER_REASON2," +
                "HOW_GET_INFORMATION, ISALONE, ISCOUPLE, ISFAMILY, ISCOLLEGUE, ISFRIEND, ISAMITY, ETC_COMPANION," +
                "NUM_OF_PEOPLE, MONOR, ISHOTEL, ISMOTEL, ISGUESTHOUSE, ISPENSION, ISRESORT, ISFRIENDS_HOUSE," +
                "NO_ACCOMODATION, ETC_ACCOMODATION, TRANSPORTATION, TRIP_TYPE, MONTH, USER_ID)" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        String UpdateQuery = "UPDATE INPUTS SET VISIT_TIME = ?, STAY_DURATION = ?, MAIN_DESTINATION = ?," +
                "CONSIDER_REASON1 = ?, CONSIDER_REASON2 = ?, HOW_GET_INFORMATION = ?, ISALONE = ?," +
                "ISCOUPLE = ?, ISFAMILY = ?,  ISCOLLEGUE = ?, ISFRIEND = ?, ISAMITY = ? , ETC_COMPANION = ?," +
                "NUM_OF_PEOPLE = ?, MONOR = ?, ISHOTEL = ?, ISMOTEL = ?, ISGUESTHOUSE = ?, ISPENSION = ?,   " +
                "ISRESORT = ?, ISFRIENDS_HOUSE = ?, NO_ACCOMODATION = ?, ETC_ACCOMODATION = ?, TRANSPORTATION = ?," +
                "TRIP_TYPE = ?, MONTH = ? WHERE USER_ID = ?";
        Object[] params = {input.getVisitTime(), input.getStayDuration(), input.getMainDestination(),
                        input.getConsiderReason1(), input.getConsiderReason2(),
                        input.getHowGetInfomation(),isAlone, isCouple, isFamily, isCollgue, isFriend,
                        isAmity, etcCompanion, input.getNumOfPeople(), input.getNumOfMinor(), isHotel,
                        isMotel, isGuestHouse,isPension, isResort, isFriendsHouse, noAccomodation, etcAccomodation,
                        input.getTransportaion(), input.getTripType(), input.getMonth(), input.getUserId()};
        try{
            jdbcTemplate.update(insertQuery, params);
        }catch (DuplicateKeyException e){
            try {
                jdbcTemplate.update(UpdateQuery, params);
            }catch(Exception e2){
                e2.printStackTrace();
            }
        }
    }
}
