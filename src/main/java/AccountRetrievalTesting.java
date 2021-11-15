import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class AccountRetrievalTesting {

    public static void main(String [] args) throws IOException, ExecutionException, InterruptedException {
        // This will print every user stored in the database
        AccountManager manager = new AccountManager();
        manager.populateUsers();
        System.out.println(manager.getUsers());

    }
}