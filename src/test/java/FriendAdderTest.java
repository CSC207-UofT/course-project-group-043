import Entities.Person;
import UseCaseClasses.FriendAdder;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class FriendAdderTest {

    @Test(timeout = 50)
    public void TestFriendRequest() {

        /* create two new users
         */
        Person sunehra = new Person("sunehra", "2323");
        Person bob = new Person("bob1", "7474");

        /* create new UseCaseClasses.FriendAdder
        */

        FriendAdder friendRequest = new FriendAdder();

        /* sunehra sends a friend request to bob
         */

        friendRequest.sendFriendRequest(sunehra, bob);
        ArrayList<Person> bobList = new ArrayList<Person>();
        bobList.add(bob);
        assertEquals(sunehra.getOutgoingRequests(), bobList);

        ArrayList<Person> sunehraList = new ArrayList<Person>();
        sunehraList.add(sunehra);
        assertEquals(bob.getIncomingRequests(), sunehraList);

        /* bob accepts sunehra's friend request.
         */

        friendRequest.acceptFriendRequest(sunehra, bob);
        ArrayList<Person> empty = new ArrayList<Person>();

        assertEquals(sunehra.getOutgoingRequests(), empty);
        assertEquals(bob.getIncomingRequests(), empty);
        assertEquals(sunehra.getUserFriends(), bobList);
        assertEquals(bob.getUserFriends(), sunehraList);
    }
}
