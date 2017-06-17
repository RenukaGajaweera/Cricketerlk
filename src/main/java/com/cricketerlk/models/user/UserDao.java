package com.cricketerlk.models.user;

import com.cricketerlk.models.player.Player;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Supun on 5/13/2017.
 */
@Transactional
@Repository
public class UserDao implements IUserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getUserById(String userId) {
        return entityManager.find(User.class, userId);
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(String userId) {

    }

    @Override
    public boolean UserExists(String email) {
        return false;
    }
}
