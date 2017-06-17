package com.cricketerlk.services;

import com.cricketerlk.models.user.User;

/**
 * Created by Supun on 5/13/2017.
 */
public interface IUserService {

    User getUserById(String userId);

    boolean addUser(User user);

    void updateUser(User user);

    void deleteUser(String userId);
}
