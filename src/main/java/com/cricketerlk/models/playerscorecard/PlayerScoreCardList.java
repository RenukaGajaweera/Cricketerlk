package com.cricketerlk.models.playerscorecard;

import com.cricketerlk.models.player.Player;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Supun on 6/4/2017.
 */

@Component
public class PlayerScoreCardList implements Serializable {

    private List<PlayerScoreCard> scoreCardList;
    private List<Player> playerList;

    public List<PlayerScoreCard> getScoreCardList() {
        return scoreCardList;
    }

    public void setScoreCardList(List<PlayerScoreCard> scoreCardList) {
        this.scoreCardList = scoreCardList;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }
}
