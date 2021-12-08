import Entities.Person;
import UseCaseClasses.AccountLogIn;
import UseCaseClasses.UserList;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountLogInTest {

    @Test(timeout = 50)
    public void TestLogIn1() {
        AccountLogIn al = new AccountLogIn();
        Person u2 = new Person("Michael", "1234");
        UserList tempData = new UserList();

        tempData.addUser("Michael", u2);

        boolean actual = al.logIn("Michael", "1234", tempData);

        assertEquals(true, actual);
    }

    @Test(timeout = 50)
    public void TestLogIn2() {
        AccountLogIn al = new AccountLogIn();
        Person u2 = new Person("Michael", "1234");
        UserList tempData = new UserList();

        tempData.addUser("Michael", u2);

        boolean actual = al.logIn("Michael", "wrongPassword", tempData);

        assertEquals(false, actual);
    }
}
