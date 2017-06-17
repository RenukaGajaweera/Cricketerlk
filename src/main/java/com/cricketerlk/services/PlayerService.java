package com.cricketerlk.services;

import com.cricketerlk.models.player.IPlayerDao;
import com.cricketerlk.models.player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Supun on 5/5/2017.
 */

@Service
public class PlayerService implements IPlayerService {

    @Autowired
    private IPlayerDao playerDao;

    @Override
    public List<Player> getAllPlayers() {
        return playerDao.getAllPLayers();
    }

    @Override
    public List<Player> getAllPlayersByCountryCode(int countryCode) {
        return playerDao.getAllPlayersByCountry(countryCode);
    }

    @Override
    public List<Player> getAllPlayersByName(String playerName) {
        return playerDao.getPlayerByName(playerName);
    }

    @Override
    public Player getPlayerById(int playerId) {
        return playerDao.getPlayerById(playerId);
    }

    @Override
    public synchronized boolean addPlayer(Player player) {
        playerDao.addPlayer(player);
        return true;
    }

    @Override
    public void updatePlayer(Player player) {

    }

    @Override
    public void deletePlayer(int articleId) {

    }
}
