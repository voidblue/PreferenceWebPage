package voidblue.preference.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import voidblue.preference.demo.Models.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void test(){
        DaoFactory daoFactory = new DaoFactory();
        InputDao inputDao = daoFactory.inputDao();
        UserDao userDao = daoFactory.userDao();
        OutputDao outputDao = daoFactory.outputDao();
        TrainingData trainingData = daoFactory.trainingData();
//        for (int i = 0 ;  i < 1000 ; i++){
//            User user = new User();
//            TrainingInput input = trainingData.trainingInput(Integer.toString(18010+i));
//            user.setId("testId"+i);
//            user.setSex(input.getGender());
//            user.setRegion(input.getRegion());
//            user.setBirth(input.getBirthYear());
//            user.setJob(input.getJob());
//            user.setEducation(input.getEducation());
//            userDao.insertUSer(user);
//
//        }

//        for (int i = 0 ;  i < 1000 ; i++){
//            TrainingInput tinput = trainingData.trainingInput(Integer.toString(18010+i));
//            Input input = new Input();
//            input.setVisitTime(tinput.getVisitTimes());
//            input.setStayDuration(tinput.getStayDuration());
//            input.setMainDestination(tinput.getMainDestination());
//            input.setConsiderReason1(tinput.getConsiderReason1());
//            input.setConsiderReason2(tinput.getConsiderReason2());
//            input.setHowGetInfomation(tinput.getInfoGet());
//            input.setTripType(tinput.getTriptype());
//            input.setTypeOfCompanion("1");
//            input.setNumOfPeople(tinput.getNumOfPeople());
//            input.setNumOfMinor(tinput.getMinor());
//            input.setAccomodation("1");
//            input.setTransportaion(tinput.getTransportaion());
//            input.setMonth(tinput.getMonth());
//            input.setUserId("testId"+i);
//
//            inputDao.tryInsertThenUpdate(input);
//        }

        for (int i = 0 ; i < 1000 ; i ++){
            Output output = trainingData.trainingOutput(Integer.toString(1+i));
            output.setUserID("testId"+i);
            outputDao.insert(output);
        }
    }

}
