package org.wlxy.example.staticData;

import org.wlxy.example.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserData {
    public  static List<User> userList;

    static {
        userList=new ArrayList<User>();
        User user1= new User();
        user1.setPassword("123");
        user1.setUserName("tom");
        User user2= new User();
        user2.setPassword("456");
        user2.setUserName("jack");
        userList.add(user1);
        userList.add(user2);
    }
}
