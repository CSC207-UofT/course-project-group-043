public class FriendAdder {
    /** Adds users to friend list by sending invitations, then accepting invitations.
     *
     * @param user1 the username of user1 who sends friend request
     * @param user2 the username of user2 who receives/ accepts friend request
     *
     */

    public void sendFriendRequest(Person user1, Person user2){
        //user 1 sends friend request to user 2
        user1.outgoingRequests.add(user2);
        user2.incomingRequests.add(user1);
    }

    public void acceptFriendRequest(Person user1, Person user2){
        //user 2 accepts user1's friend request
         user2.incomingRequests.remove(user1);
         user1.outgoingRequests.remove(user2);
         user1.userFriends.add(user2);
         user2.userFriends.add(user1);
    }
}
