package com.cricketerlk.models.player;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Supun on 5/5/2017.
 */

@Transactional
@Repository
public class PlayerDao implements IPlayerDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Player> getAllPLayers() {
        String hql = "FROM Player as players ORDER BY players.playerId";
        return (List<Player>) entityManager.createQuery(hql).getResultList();
    }

    @Override
    public Player getPlayerById(int playerId) {
        return entityManager.find(Player.class, playerId);
    }

    @Override
    public List<Player> getPlayerByName(String playerName) {
        String hql = "FROM Player as players WHERE players.playerName=:playerName ORDER BY players.playerId";
        return (List<Player>) entityManager.createQuery(hql)
                .setParameter("playerName", playerName)
                .getResultList();
    }

    @Override
    public List<Player> getAllPlayersByCountry(int countryCode) {
        String hql = "FROM Player as players WHERE players.country=:country ORDER BY players.playerId";
        return (List<Player>) entityManager.createQuery(hql)
                .setParameter("country", countryCode)
                .getResultList();
    }

    @Override
    public void addPlayer(Player player) {
        entityManager.persist(player);
    }

    @Override
    public void updatePlayer(Player player) {

    }

    @Override
    public void deletePlayer(int playerId) {

    }

    @Override
    public boolean playerExists(String name, String country) {
        return false;
    }
}
