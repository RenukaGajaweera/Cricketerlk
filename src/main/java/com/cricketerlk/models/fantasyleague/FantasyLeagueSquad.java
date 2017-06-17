package com.cricketerlk.models.fantasyleague;

import org.hibernate.validator.constraints.NotBlank;
import org.omg.CORBA.Object;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Supun on 6/5/2017.
 */

@Entity
@Table(name = "fantasy_league_squad")
public class FantasyLeagueSquad {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "squad_id")
    private int squadId;

    @Column(name = "squad_league_id")
    @NotNull
    private int leagueId;

    @Column(name = "squad_player_id")
    @NotNull
    private int playerId;

    public int getSquadId() {
        return squadId;
    }

    public void setSquadId(int squadId) {
        this.squadId = squadId;
    }

    public int getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

}
