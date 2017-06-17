package com.cricketerlk.models.player;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Supun on 5/5/2017.
 */

@Entity
@Table(name = "players")
public class Player implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "player_id")
    private int playerId;

    @Column(name = "country")
    @NotNull
    private int country;

    @Column(name = "player_type")
    @NotNull
    private int playerType;

    @Column(name = "wicket_keeper")
    private boolean wicketKeeper;

    @Column(name = "player_name", columnDefinition = "char")
    @NotNull
    private String playerName;

    public int getPlayerId() {
        return playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getCountry() {
        return country;
    }

    public int getPlayerType() {
        return playerType;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setCountry(int country) {
        this.country = country;
    }

    public void setPlayerType(int playerType) {
        this.playerType = playerType;
    }

    public boolean isWicketKeeper() {
        return wicketKeeper;
    }

    public void setWicketKeeper(boolean wicketKeeper) {
        this.wicketKeeper = wicketKeeper;
    }
}
