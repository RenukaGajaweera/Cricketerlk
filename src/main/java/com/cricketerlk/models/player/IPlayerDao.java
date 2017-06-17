package com.cricketerlk.models.player;

import java.util.List;

/**
 * Created by Supun on 5/5/2017.
 */
public interface IPlayerDao {

    List<Player> getAllPLayers();

    Player getPlayerById(int playerId);

    List<Player> getPlayerByName(String playerName);

    List<Player> getAllPlayersByCountry(int countryCode);

    void addPlayer(Player player);

    void updatePlayer(Player player);

    void deletePlayer(int playerId);

    boolean playerExists(String name, String country);
}
