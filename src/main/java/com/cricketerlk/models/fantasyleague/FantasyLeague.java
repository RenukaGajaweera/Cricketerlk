package com.cricketerlk.models.fantasyleague;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Supun on 6/5/2017.
 */

@Entity
@Table(name = "fantasy_league")
public class FantasyLeague {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "league_id")
    private int leagueId;

    @Column(name = "user_id")
    @NotBlank
    private String userId;

    @Column(name = "match_id")
    @NotNull
    private int matchId;

    @Column(name = "structure_code")
    @NotNull
    private int structureCode;

    @Column(name = "batting_score")
    private float battingScore;

    @Column(name = "bowling_score")
    private float bowlingScore;

    @Column(name = "fielding_score")
    private float fieldingScore;

    @Column(name = "extra")
    private float extra;

    @Column(name = "mom")
    private int mom;

    @Column(name = "captain")
    private int captain;

    @Column(name = "vice_captain")
    private int viceCaptain;

    public int getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(int leagueId) {
        this.leagueId = leagueId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getStructureCode() {
        return structureCode;
    }

    public void setStructureCode(int structureCode) {
        this.structureCode = structureCode;
    }

    public float getBattingScore() {
        return battingScore;
    }

    public void setBattingScore(float battingScore) {
        this.battingScore = battingScore;
    }

    public float getBowlingScore() {
        return bowlingScore;
    }

    public void setBowlingScore(float bowlingScore) {
        this.bowlingScore = bowlingScore;
    }

    public float getFieldingScore() {
        return fieldingScore;
    }

    public void setFieldingScore(float fieldingScore) {
        this.fieldingScore = fieldingScore;
    }

    public float getExtra() {
        return extra;
    }

    public void setExtra(float extra) {
        this.extra = extra;
    }

    public int getMom() {
        return mom;
    }

    public void setMom(int mom) {
        this.mom = mom;
    }

    public int getCaptain() {
        return captain;
    }

    public void setCaptain(int captain) {
        this.captain = captain;
    }

    public int getViceCaptain() {
        return viceCaptain;
    }

    public void setViceCaptain(int viceCaptain) {
        this.viceCaptain = viceCaptain;
    }
}
