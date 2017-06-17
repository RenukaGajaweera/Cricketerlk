package com.cricketerlk.models.fantasyleague;

import java.util.List;

/**
 * Created by Supun on 6/5/2017.
 */
public interface IFantasyLeagueDao {

    FantasyLeague getFantasyLeague(String playerId, int matchId);

    List<FantasyLeague> getFantasyLeagueByMatch(int matchId);

    List<FantasyLeague> getFantasyLeagueByPlayer(int playerId);

    void addFantasyLeague(FantasyLeague fantasyLeague);

    FantasyLeague updateFantasyLeague(FantasyLeague fantasyLeague);

}
