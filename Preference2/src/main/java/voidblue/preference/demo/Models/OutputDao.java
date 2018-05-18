package voidblue.preference.demo.Models;

import lombok.AllArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;

@AllArgsConstructor
public class OutputDao {
    private final JdbcTemplate jdbcTemplate;


    public void insert(Output output) {

        String sqlForInsert = "INSERT INTO OUTPUTS(한라산, 오름, 성산일출봉, 섬, 올레길, 폭포, 동굴, 해수욕장, 비자림, 한라수목원," +
                "서귀포자연휴양림, 절물자연휴양림, 용두암, 주상절리대, 한림공원, 국립제주박물관, 도립미술관, 민속자연사박물관, 제주돌문화공원," +
                "제주세계자연유산센터, 이중섭박물관, 서복전시관, 제주43평화공원, 동문시장, 중앙로지하상가, 바오젠거리," +
                "제주오일장, 서귀포매일올레시장, 신라면세점, 롯데면세점, 제주관광공사면세점, 공항JDC면세점, 제주목관아," +
                "항몽유적지, 성읍민속마을, 삼양동선사유적, 제주추사관, 관덕정, 이중섭거주지, 하멜기념비, 미로공원, 에코랜드," +
                "제주경마공원, 불교사찰, 아쿠아플라넷, 테디베어박물관, 소인국테마파크, 잠수함관광, 신비의도로, 생각하는정원," +
                "드라마촬영지, 제주별빛누리공원, 유람선, 제주바다체험장, 골프장, 카지노, USER_ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";

        String sqlForUpdate = "UPDATE OUTPUTS SET 한라산 = ?, 오름 = ? , 성산일출봉 = ?, 섬 = ?, 올레길 = ?, 폭포 = ?," +
                " 동굴 = ?, 해수욕장 = ?, 비자림 = ?, 한라수목원 = ?, 서귀포자연휴양림 = ?, 절물자연휴양림 = ?, 용두암 = ?, 주상절리대 = ?," +
                " 한림공원 = ?, 국립제주박물관 = ?, 도립미술관 = ?, 민속자연사박물관 = ?, 제주돌문화공원 = ?, " +
                " 제주세계자연유산센터 = ?, 이중섭박물관 = ?, 서복전시관 = ?, 제주43평화공원 = ?, 동문시장 = ?, 중앙로지하상가 = ?," +
                " 바오젠거리 = ?, 제주오일장 = ?, 서귀포매일올레시장 = ?, 신라면세점 = ?, 롯데면세점 = ?, 제주관광공사면세점 = ?," +
                " 공항JDC면세점 = ?, 제주목관아 = ?. 항몽유적지 = ?, 성읍민속마을 = ?, 삼양동선사유적 = ?, 제주추사관 = ?," +
                " 관덕정 = ?, 이중섭거주지 = ?, 하멜기념비 = ?, 미로공원 = ?, 에코랜드 = ?, 제주경마공원 = ?, 불교사찰 = ?," +
                " 아쿠아플라넷 = ?, 테디베어박물관 = ?, 소인국테마파크 = ?, 잠수함관광 = ?, 신비의도로 = ?, 생각하는정원 = ?," +
                " 드라마촬영지 = ?, 제주별빛누리공원 = ?, 유람선 = ?, 제주바다체험장 = ?, 골프장 = ?, 카지노 = ? WHERE USER_ID = ?";


        Object[] params = {output.get한라산(), output.get오름(), output.get성산일출봉(), output.get섬(), output.get올레길(),
                output.get폭포(), output.get동굴(), output.get해수욕장(), output.get비자림(), output.get한라수목원(),
                output.get서귀포자연휴양림(),output.get절물자연휴양림(), output.get용두암(), output.get주상절리대(), output.get한림공원(),
                output.get국립제주박물관(), output.get도립미술관(), output.get민속자연사박물관(), output.get제주43평화공원(),
                output.get제주세계자연유산센터(), output.get이중섭박물관(), output.get서복전시관(), output.get제주돌문화공원(),
                output.get동문시장(), output.get중앙로지하상가(), output.get바오젠거리(), output.get제주오일장(),
                output.get서귀포매일올레시장(), output.get신라면세점(), output.get롯데면세점(), output.get제주관광공사면세점(),
                output.get공항JDC면세점(), output.get제주목관아(), output.get항몽유적지(), output.get성읍민속마을(),
                output.get삼양동선사유적(), output.get제주추사관(), output.get관덕정(), output.get이중섭거주지(),
                output.get하멜기념비(), output.get미로공원(), output.get에코랜드(), output.get제주경마공원(),
                output.get불교사찰(),  output.get아쿠아플라넷(), output.get테디베어박물관(), output.get소인국테마파크(),
                output.get잠수함관광(), output.get신비의도로(), output.get생각하는정원(), output.get드라마촬영지(),
                output.get제주별빛누리공원(), output.get유람선(), output.get제주바다체험장(), output.get골프장(),
                output.get카지노(), output.getUserID()};


        try {
            jdbcTemplate.update(sqlForInsert, params);
        }catch (DuplicateKeyException e){
            try{
                jdbcTemplate.update(sqlForUpdate, params);
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
    }
}
