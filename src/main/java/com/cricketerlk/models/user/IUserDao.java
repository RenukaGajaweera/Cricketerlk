package com.cricketerlk.models.user;

/**
 * Created by Supun on 5/12/2017.
 */
public interface IUserDao {
    
    User getUserById(String userId);

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(String userId);

    boolean UserExists(String email);
}
