import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

public class JSONTest {
    @Test
    public void test(){
//        Recommend recommend = Recommend.getTempExcutedIntance();
//        String[] result = recommend.getResult().split("ESC");

        String[] result = {" ㅌㅌㅌㅌㅌ", "여행지", "(0,0)", "여행지", "(0,0)", "여행지", "(0,0)", "여행지", "(0,0)", "여행지", "(0,0)"};

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        for (int i = 1; i < result.length ; i++){
            if (i % 2 == 1) {
                String str = (i/2 + 1) + "순위 여행지";
                try {
                    jsonObject.put(str ,result[i]);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else {
                try {
                    jsonObject.put("좌표", result[i]);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                jsonArray.put(jsonObject);
                jsonObject = new JSONObject();

            }
        }
        System.out.println(jsonArray.toString());
    }

}
