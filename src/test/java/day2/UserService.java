package day2;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private List<PojoUser> userList = new ArrayList<>();

    public void addUser(PojoUser user) {
        userList.add(user);
    }

    public List<PojoUser> getAllUsers() {
        return userList;
    }

    public boolean deleteUser(PojoUser user) {
        return userList.remove(user);
    }
}
