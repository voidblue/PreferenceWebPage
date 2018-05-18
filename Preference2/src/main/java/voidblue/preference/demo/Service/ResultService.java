package voidblue.preference.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import voidblue.preference.demo.Models.Output;
import voidblue.preference.demo.Models.OutputDao;

@Service
public class ResultService {
    @Autowired
    OutputDao outputDao;
    public void tryInsertThenUpdate(String selected, String userId) {
        Output output = new Output();
        switch (selected){
            case "한라산":
                output.set한라산(1);
                break;
            case "오름":
                output.set오름(1);
                break;
            case "성산일출봉":
                output.set성산일출봉(1);
                break;
            case "섬":
                output.set섬(1);
                break;
            case "올레길":
                output.set올레길(1);
                break;
            case "폭포":
                output.set폭포(1);
                break;
            case "동굴":
                output.set동굴(1);
                break;
            case "해수욕장":
                output.set해수욕장(1);
                break;
            case "비자림":
                output.set비자림(1);
                break;
            case "한라수목원":
                output.set한라수목원(1);
                break;
            case "서귀포자연휴양림":
                output.set서귀포자연휴양림(1);
                break;
            case "절물자연휴양림":
                output.set절물자연휴양림(1);
                break;
            case "용두암":
                output.set용두암(1);
                break;
            case "주상절리대":
                output.set주상절리대(1);
                break;
            case "한림공원":
                output.set한림공원(1);
                break;
            case "국립제주박물관":
                output.set국립제주박물관(1);
                break;
            case "도립미술관":
                output.set도립미술관(1);
                break;
            case "민속자연사박물관":
                output.set민속자연사박물관(1);
                break;
            case "제주돌문화공원":
                output.set제주돌문화공원(1);
                break;
            case "제주세계자연유산센터":
                output.set제주세계자연유산센터(1);
                break;
            case "이중섭박물관":
                output.set이중섭박물관(1);;
                break;
            case "서복전시관":
                output.set서복전시관(1);
                break;
            case "제주43평화공원":
                output.set제주43평화공원(1);
                break;
            case "동문시장":
                output.set동문시장(1);
                break;
            case "중앙로지하상가":
                output.set중앙로지하상가(1);
                break;
            case "바오젠거리":
                output.set바오젠거리(1);
                break;
            case "제주오일장":
                output.set제주오일장(1);
                break;
            case "서귀포매일올레시장":
                output.set서귀포매일올레시장(1);
                break;
            case "신라면세점":
                output.set신라면세점(1);
                break;
            case "롯데면세점":
                output.set롯데면세점(1);
                break;
            case "제주관광공사면세점":
                output.set제주관광공사면세점(1);
                break;
            case "공항JDC면세점":
                output.set공항JDC면세점(1);
                break;
            case "제주목관아":
                output.set제주목관아(1);
                break;
            case "항목유적지":
                output.set항몽유적지(1);
                break;
            case "성읍민속마을":
                output.set성읍민속마을(1);
                break;
            case "삼양동선사유적":
                output.set삼양동선사유적(1);
                break;
            case "제주추사관":
                output.set제주추사관(1);
                break;
            case "관덕정":
                output.set관덕정(1);
                break;
            case "이중섭거주지":
                output.set이중섭거주지(1);
                break;
            case "하멜기념비":
                output.set하멜기념비(1);
                break;
            case "미로공원":
                output.set미로공원(1);
                break;
            case "에코랜드":
                output.set에코랜드(1);
                break;
            case "제주경마공원":
                output.set제주경마공원(1);
                break;
            case "불교사찰":
                output.set불교사찰(1);
                break;
            case "아쿠아플라넷":
                output.set아쿠아플라넷(1);
                break;
            case "테디베어박물관":
                output.set테디베어박물관(1);
                break;
            case "소인국테마파크":
                output.set소인국테마파크(1);
                break;
            case "잠수함관광":
                output.set잠수함관광(1);
                break;
            case "신비의도로":
                output.set신비의도로(1);
                break;
            case "생각하는정원":
                output.set생각하는정원(1);
                break;
            case "드라마촬영지":
                output.set드라마촬영지(1);
                break;
            case "제주별빛누리공원":
                output.set제주별빛누리공원(1);
                break;
            case "유람선":
                output.set유람선(1);
                break;
            case "제주바다체험장":
                output.set제주바다체험장(1);
                break;
            case "골프장":
                output.set골프장(1);
                break;
            case "카지노":
                output.set카지노(1);
                break;
        }
        output.setUserID(userId);


        outputDao.insert(output);
    }
}
