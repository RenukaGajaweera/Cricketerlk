package com.cricketerlk.services;

import com.cricketerlk.models.player.Player;

import java.util.List;

/**
 * Created by Supun on 5/5/2017.
 */
public interface IPlayerService {

    List<Player> getAllPlayers();

    List<Player> getAllPlayersByCountryCode(int countryCode);

    List<Player> getAllPlayersByName(String playerName);

    Player getPlayerById(int playerId);

    boolean addPlayer(Player player);

    void updatePlayer(Player player);

    void deletePlayer(int playerId);

}
