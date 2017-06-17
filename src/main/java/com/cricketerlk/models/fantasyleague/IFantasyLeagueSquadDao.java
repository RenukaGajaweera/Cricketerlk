package com.cricketerlk.models.fantasyleague;

import java.util.List;

/**
 * Created by Supun on 6/5/2017.
 */
public interface IFantasyLeagueSquadDao {

    List<FantasyLeagueSquad> getSquadByPlayId(int playId);

    void addFantasyLeagueSquad(List<FantasyLeagueSquad> squad);

    List<FantasyLeagueSquad> updateSquad(List<FantasyLeagueSquad> squad);

}
