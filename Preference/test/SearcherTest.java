import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SearcherTest {

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql://220.149.42.125:3306/preference?useUnicode=true&characterEncoding=euckr" ,
                "root", "456111");
        return connection;
    }

    @Test
    public void test() throws SQLException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
//        String[] arr = new String[] {"1", "1995", "3", "1", "6", "1", "0", "0", "0", "0", "1", "0", "0", "11", "3", "7", "1", "1", "1", "2", "2", "3", "0", "0", "0", "0", "1", "0","0", "0", "0"};

        ArrayList testsetList = getTestsetList();

        for (int i = 0; i < testsetList.size(); i++)
        {
            String[] inputArr = (String[]) testsetList.get(i);
            DBSearcher searcher = DBSearcher.getInstance(inputArr);
            System.out.println(searcher.getOthersSelect());
        }

    }

    private ArrayList getTestsetList() throws SQLException, ClassNotFoundException {
        String testsetSQL = "select visitTimes, birthYear, transportaion, numOfPeople, mainDestination, " +
                "alone, couple, family, collegue, friends, amity, othersCompanion, job, stayDuration, " +
                "considerReason1, triptype, month, region, considerReason2, gender, education, infoGet," +
                "minor, hotel, motel, guestHouse, pension, resort, friendsHouse, noAccomodation, " +
                "otherAccomodation " +
                "from trainingOutput " +
                "JOIN trainingInput ON (trainingInput.key-18009) = trainingOutput.key " +
                "Where 한라산+오름+성산일출봉+섬+올레길+폭포+동굴+해수욕장+비자림+한라수목원+서귀포자연휴양림+절물자연후양림" +
                "+용두암+주상절리대+한림공원+국립제주방물관+도립미술관+민속자연사박물관+제주돌문화공원+제주세계자연유산센터+" +
                "이중섭박물관+서복전시관+제주43평화공원+동문시장+중앙로지하상가+바오젠거리+제주오일장+서귀포매일올레시장+" +
                "신라면세점+롯데면세점+제주관광공사면세점+공항JDC면세점+제주목관아+항몽유적지+성읍민속마을+삼양동선사유적" +
                "+제주추사관+관덕정+이중섭거주지+하멜기념비+미로공원+에코랜드+제주경마공원+불교사찰+아쿠아플라넷+" +
                "테디베어박물관+소인국테마파크+잠수함관광+신비의도로+생각하는정원+드라마촬영지+제주별빛누리공원+" +
                "유람선+제주바다체험장+골프장+카지노 >= 7;";

        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(testsetSQL);
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData meta = resultSet.getMetaData();
//        resultSet.next();
//        System.out.println(resultSet.getInt("birthYear"));

        ArrayList testsetList = new ArrayList();
        while (resultSet.next())
        {
            String[] testsetData = new String[31];

            for (int i = 1; i <= meta.getColumnCount(); i++)
            {
                String field = meta.getColumnName(i);
                int value = resultSet.getInt(field);
                testsetData[i-1] = Integer.toString(value);
//                System.out.println(num);
            }

            testsetList.add(testsetData);
        }

        return testsetList;
    }
}
