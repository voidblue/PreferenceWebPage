import org.junit.jupiter.api.Test;

public class RecommendTest {
    @Test
    public void test(){
        Recommend recommend = Recommend.getTempExcutedIntance();
        String result = recommend.getResult();
        System.out.println(result);
    }
}
