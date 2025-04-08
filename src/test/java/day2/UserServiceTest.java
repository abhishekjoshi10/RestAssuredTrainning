package day2;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.List;

public class UserServiceTest {

    @Test
    public void testAddMultipleUsers() {
        UserService userService = new UserService();

        PojoUser user1 = new PojoUser("Alice", 25);
        PojoUser user2 = new PojoUser("Bob", 30);
        PojoUser user3 = new PojoUser("Charlie", 35);

        userService.addUser(user1);
        userService.addUser(user2);
        userService.addUser(user3);

        List<PojoUser> users = userService.getAllUsers();

        assertEquals(users.size(), 3);
        assertTrue(users.contains(user1));
        assertTrue(users.contains(user2));
        assertTrue(users.contains(user3));
    }

    @Test
    public void testDeleteUser() {
        UserService userService = new UserService();

        PojoUser user1 = new PojoUser("Alice", 25);
        PojoUser user2 = new PojoUser("Bob", 30);

        userService.addUser(user1);
        userService.addUser(user2);

        boolean removed = userService.deleteUser(user1);

        List<PojoUser> users = userService.getAllUsers();

        assertTrue(removed);
        assertEquals(users.size(), 1);
        assertFalse(users.contains(user1));
        assertTrue(users.contains(user2));
    }
}
