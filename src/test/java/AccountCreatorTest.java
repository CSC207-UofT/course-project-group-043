import Entities.Person;
import UseCaseClasses.AccountCreator;
import UseCaseClasses.UserList;
import org.junit.Test;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

public class AccountCreatorTest {

    @Test(timeout = 50)
    public void TestCheckValidity1() throws ExecutionException, InterruptedException {
        AccountCreator ac = new AccountCreator();
        Person u2 = new Person();
        UserList tempData = new UserList();
        System.out.println(tempData.users);
        tempData.addUser("user123", u2);

        boolean actual = ac.makeAccount("user123", "1234", "Helen", tempData);

        assertEquals(false, actual);
    }

    @Test(timeout = 50)
    public void TestCheckValidity2() throws ExecutionException, InterruptedException {
        AccountCreator ac = new AccountCreator();
        Person u2 = new Person();
        UserList tempData = new UserList();

        tempData.addUser("user123", u2);

        boolean actual = ac.makeAccount("sparrow", "4321", "Helen", tempData);

        assertEquals(true, actual);
    }

}
