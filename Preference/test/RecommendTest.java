import org.junit.jupiter.api.Test;

public class RecommendTest {
    @Test
    public void test(){
        Recommend recommend = Recommend.getTempExcutedIntance();
        String[] result = recommend.getResult().split("ESC");
        for (String e : result) {
            System.out.println(e);
        }

    }
}
