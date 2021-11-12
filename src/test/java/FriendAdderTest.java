import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class FriendAdderTest {

    @Test(timeout = 50)
    public void TestFriendRequest() {

        /* create two new users
         */
        Person Sunehra = new Person("sunehra", "2323");
        Person Bob = new Person("bob1", "7474");

        /* create new FriendAdder
        */

        FriendAdder friendRequest = new FriendAdder();

        /* Sunehra sends a friend request to Bob
         */

        friendRequest.sendFriendRequest(Sunehra, Bob);
        ArrayList<Person> BobList = new ArrayList<Person>();
        BobList.add(Bob);
        assertEquals(Sunehra.getOutgoingRequests(), BobList);

        ArrayList<Person> SunehraList = new ArrayList<Person>();
        SunehraList.add(Sunehra);
        assertEquals(Bob.getIncomingRequests(), SunehraList);

        /* Bob accepts Sunehra's friend request.
         */

        friendRequest.acceptFriendRequest(Sunehra, Bob);
        ArrayList<Person> empty = new ArrayList<Person>();

        assertEquals(Sunehra.getOutgoingRequests(), empty);
        assertEquals(Bob.getIncomingRequests(), empty);
        assertEquals(Sunehra.getUserFriends(), BobList);
        assertEquals(Bob.getUserFriends(), SunehraList);
    }
}
