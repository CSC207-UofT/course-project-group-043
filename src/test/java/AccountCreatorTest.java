import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class AccountCreatorTest {

    @Test(timeout = 50)
    public void TestCheckValidity1() {
        AccountCreator ac = new AccountCreator();
        Person u1 = new Person();
        Person u2 = new Person();
        HashMap<String, Person> tempData = new HashMap<>();

        tempData.put("user123", u2);

        boolean actual = ac.makeAccount(u1, "user123", "1234", "Helen", tempData);

        assertEquals(false, actual);
    }

    @Test(timeout = 50)
    public void TestCheckValidity2() {
        AccountCreator ac = new AccountCreator();
        Person u1 = new Person();
        Person u2 = new Person();
        HashMap<String, Person> tempData = new HashMap<>();

        tempData.put("user123", u2);

        boolean actual = ac.makeAccount(u1, "sparrow", "4321", "Helen", tempData);

        assertEquals(true, actual);
    }

}
