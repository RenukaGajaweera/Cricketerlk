package com.cricketerlk.services;

import com.cricketerlk.models.fantasyleague.DetailedFantasyLeague;
import com.cricketerlk.models.fantasyleague.FantasyLeague;

import java.util.List;

/**
 * Created by Supun on 6/5/2017.
 */
public interface IFantasyLeagueService {

    DetailedFantasyLeague retrieveMatchLeagueDetails(int matchId, String userId);

    List<DetailedFantasyLeague> retrievePlayerLeagueDetails(String userId);

    boolean addStorePlayerFantasyLeagueTeam(DetailedFantasyLeague fantasyTeam);

    DetailedFantasyLeague updatePlayerFantasyLeagueTeam(DetailedFantasyLeague fantasyTeam);

    boolean fantasyLeagueExist(int matchId, String userId);

    boolean createFantasyLeague(String userId, int matchId, int structure);

    FantasyLeague retrieveFantasyLeague(String userId, int matchId);

    public boolean updateFantasyLeagueScores(int matchId);

    public void updateFantasyLeagueScoresNullEntries(int matchId);
}
