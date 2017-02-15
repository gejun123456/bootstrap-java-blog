package basic;

import com.App;
import com.google.gson.Gson;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by bruce.ge on 2016/10/23.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class AbstractTest {
    private static Gson gson = new Gson();
    protected void printToGson(Object o){
        System.out.println(gson.toJson(o));
    }
}
