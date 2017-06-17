package com.cricketerlk.models.fantasyleague;

import com.cricketerlk.models.playerscorecard.PlayerScoreCard;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Supun on 6/5/2017.
 */

@Component
public class DetailedFantasyLeague implements Serializable {

    private FantasyLeague leagueMetaDate;

    private List<FantasyLeagueSquad> leagueSquad;

    public FantasyLeague getLeagueMetaDate() {
        return leagueMetaDate;
    }

    public void setLeagueMetaDate(FantasyLeague leagueMetaDate) {
        this.leagueMetaDate = leagueMetaDate;
    }

    public List<FantasyLeagueSquad> getLeagueSquad() {
        return leagueSquad;
    }

    public void setLeagueSquad(List<FantasyLeagueSquad> leagueSquad) {
        this.leagueSquad = leagueSquad;
    }

}
