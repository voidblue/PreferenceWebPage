package voidblue.preference.demo.Models;

import org.springframework.jdbc.core.JdbcTemplate;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class TrainingData {
    JdbcTemplate jdbcTemplate;
    public TrainingData(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    public TrainingInput trainingInput(String key){
        String sql = "select * from trainingInput where `key` = ?";
        Object[] params = {key};
            return jdbcTemplate.queryForObject(sql, params, ((rs, rowNum) -> {
            TrainingInput trainingInput = new TrainingInput();
//            rs.next();
            trainingInput.setKey(rs.getString(1));
            trainingInput.setVisitTimes(rs.getString(2));
            trainingInput.setStayDuration(rs.getString(3));
            trainingInput.setMainDestination(rs.getString(4));
            trainingInput.setConsiderReason1(rs.getString(5));
            trainingInput.setConsiderReason2(rs.getString(6));
            trainingInput.setInfoGet(rs.getString(7));
            trainingInput.setAlone(rs.getString(8));
            trainingInput.setCouple(rs.getString(9));
            trainingInput.setFamily(rs.getString(10));
            trainingInput.setCollegue(rs.getString(11));
            trainingInput.setFriends(rs.getString(12));
            trainingInput.setAmity(rs.getString(13));
            trainingInput.setOthersCompanion(rs.getString(14));
            trainingInput.setNumOfPeople(rs.getString(15));
            trainingInput.setMinor(rs.getString(16));
            trainingInput.setHotel(rs.getString(17));
            trainingInput.setMotel(rs.getString(18));
            trainingInput.setGuestHouse(rs.getString(19));
            trainingInput.setPension(rs.getString(20));
            trainingInput.setResort(rs.getString(21));
            trainingInput.setFriendsHouse(rs.getString(22));
            trainingInput.setNoAccomodation(rs.getString(23));
            trainingInput.setOtherAccomodation(rs.getString(24));
            trainingInput.setTransportaion(rs.getString(25));
            trainingInput.setTriptype(rs.getString(26));
            trainingInput.setRegion(rs.getString(27));
            trainingInput.setGender(rs.getString(28));
            trainingInput.setEducation(rs.getString(29));
            trainingInput.setBirthYear(rs.getString(30));
            trainingInput.setJob(rs.getString(31));
            trainingInput.setMonth(rs.getString(32));
            return trainingInput;
        }));

    }

    public Output trainingOutput(String key){
        String sql = "select * from trainingOutput where `key` = ?";
        Object[] params = {key};
        return jdbcTemplate.queryForObject(sql, params, ((rs, rowNum) -> {
            Output trainingOutput = new Output();
            Field[] fields = trainingOutput.getClass().getDeclaredFields();
            for (int i = 0 ; fields.length -1 > i ; i++){
                try {
                    fields[i].setAccessible(true);
                    fields[i].setInt(trainingOutput, rs.getInt(i+2));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(trainingOutput);
            return trainingOutput;
        }));

    }
}
