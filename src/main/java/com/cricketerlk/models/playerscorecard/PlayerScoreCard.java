package com.cricketerlk.models.playerscorecard;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Supun on 6/4/2017.
 */

@Entity
@Table(name = "player_match_score_card")
public class PlayerScoreCard implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "score_card_id")
    private int scoreCardId;

    @Column(name = "match_id")
    @NotNull
    private int matchId;

    @Column(name = "player_id")
    @NotNull
    private int playerId;

    @Column(name = "batting_score")
    private float battingScore;

    @Column(name = "bowling_score")
    private float bowlingScore;

    @Column(name = "fielding_score")
    private float fieldingScore;

    @Column(name = "extra")
    private float extra;

    @Column(name = "mom")
    private boolean mom;

    public int getScoreCardId() {
        return scoreCardId;
    }

    public void setScoreCardId(int scoreCardId) {
        this.scoreCardId = scoreCardId;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
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

    public boolean isMom() {
        return mom;
    }

    public void setMom(boolean mom) {
        this.mom = mom;
    }
}
