import Entities.Person;
import UseCaseClasses.AccountEditor;
import UseCaseClasses.UserList;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountEditorTest {
    @Test(timeout = 50)
    public void TestChangePassword() {
        AccountEditor ae = new AccountEditor();
        Person u2 = new Person("Michael", "1234");
        String newPass = "newPassword";
        ae.changePassword(u2, newPass);

        boolean actual = u2.getUserPassword().equals(newPass);

        assertEquals(true, actual);
    }

    @Test(timeout = 50)
    public void TestSecurityCheck() {
        AccountEditor ae = new AccountEditor();
        Person u2 = new Person();
        u2.setTrustedAnswer("test");

        boolean actual = ae.securityCheck(u2, "test");

        assertEquals(true, actual);
    }

    @Test(timeout = 50)
    public void TestUserExists() {
        AccountEditor ae = new AccountEditor();
        UserList data = new UserList();
        Person u2 = new Person("Michael", "1234");
        data.addUser("Michael", u2);

        boolean actual = ae.userExists("Michael", data);

        assertEquals(true, actual);
    }
}
