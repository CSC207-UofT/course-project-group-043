package ToBeIgnored;

import InterfaceAdapters.AccountManager;
import InterfaceAdapters.ScheduleManager;
import Entities.events.*;
import Entities.Person;
import UseCaseClasses.UserList;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class AccountCreationTesting {
    public static void main(String [] args) throws IOException, ExecutionException, InterruptedException {
        // this file can be edited if you wish to add a different user and different courses to the database
        AccountManager manager = new AccountManager();
        ScheduleManager sManager = new ScheduleManager();
        UserList users = new UserList();
        manager.createAccount("Dennis Tat", "123", "123");
        Person Dennis = users.getUser("Dennis Tat");
        sManager.addEvent("Course", "CSC207", "Tuesday", 16, 17, Dennis);
        sManager.addEvent("Course", "MAT334", "Tuesday", 17, 21, Dennis);
        sManager.addEvent("Course", "STA257", "Wednesday", 17, 21, Dennis);
    }
}