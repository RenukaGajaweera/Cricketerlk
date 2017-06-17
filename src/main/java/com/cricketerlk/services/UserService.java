package com.cricketerlk.services;

import com.cricketerlk.models.user.IUserDao;
import com.cricketerlk.models.user.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by Supun on 5/13/2017.
 */

@Service
public class UserService implements IUserService {

    private static Logger logger = Logger.getLogger(UserService.class);

    @Autowired
    private IUserDao userDao;

    @Override
    public User getUserById(String userId) {
        User user = userDao.getUserById(userId);
        return user;
    }

    @Override
    public boolean addUser(User user) {
        logger.info("SAVING USER:" + user.getUserId() + "-" + user.getEmail() + "-" + user.getTeamName());
        userDao.addUser(user);
        return true;
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(String userId) {

    }
}
