import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class AccountCreatorTest {

    @Test(timeout = 50)
    public void TestCheckValidity1() {
        AccountCreator ac = new AccountCreator();
        Person u = new Person();
        HashMap<String, String> tempData = new HashMap<>();

        tempData.put("user123", "1234");

        boolean actual = ac.makeAccount(u, "user123", "1234", "Helen", tempData);

        assertEquals(false, actual);
    }

    @Test(timeout = 50)
    public void TestCheckValidity2() {
        AccountCreator ac = new AccountCreator();
        Person u = new Person();
        HashMap<String, String> tempData = new HashMap<>();

        tempData.put("user123", "1234");

        boolean actual = ac.makeAccount(u, "sparrow", "4321", "Helen", tempData);

        assertEquals(true, actual);
    }

}
