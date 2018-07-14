package voidblue.preference.demo.Models;

import lombok.Getter;
import lombok.Setter;

@Setter
public class User {
    @Getter
    private String id;
    private String sex;
    private String education;
    private String job;
    @Getter
    private String birth;
    @Getter
    private String region;

    public String getSex() {
        String result = null;
        System.out.println(sex);
        if (sex.equals("남자")){result = "1";}
        else if(sex.equals("여자")){result="2";}
        return result;
    }

    public String getEducation() {
        String result = null;
        if(education.equals("고졸이하")) result = "1";
        else if(education.equals("대학생(휴학생포함)")) result = "2";
        else if(education.equals("대학졸업")) result = "3";
        else if(education.equals("대학원졸업 이상")) result = "4";
        else{
            result = education;
        }
        return result;
    }

    public String getJob() {
        String result = null;
        if(job.equals("관리자")) result = "1";
        else if(job.equals("전문가 및 관련 종사자")) result = "2";
        else if(job.equals("사무종사자")) result = "3";
        else if(job.equals("서비스종사자")) result = "4";
        else if(job.equals("판매종사자")) result = "5";
        else if(job.equals("농림어업숙련 종사자" )) result = "6";
        else if(job.equals("기능원 및 관련 기능종사자")) result = "7";
        else if(job.equals("장치기계조작 및 조립종사자 : ")) result = "8";
        else if(job.equals("단순노무자")) result = "9";
        else if(job.equals("군인/공무원")) result = "10";
        else if(job.equals("학생")) result = "11";
        else if(job.equals("주부")) result = "12";
        else if(job.equals("무직(은퇴자 포함)")) result = "13";
        else if(job.equals("기타")) result = "14";
        else result = job;

        return result;
    }

}
