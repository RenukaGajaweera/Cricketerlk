package com.cricketerlk.models.leaderboard;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Supun on 6/8/2017.
 */

@Entity
@Table(name = "leaderboard")
public class LeaderBoardResult {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "leaderboard_id")
    private int leaderboardId;

    @Column(name = "user_id")
    @NotBlank
    private String userId;

    @Column(name = "user_email")
    @NotBlank
    private String userEmail;

    @Column(name = "team_name")
    @NotBlank
    private String teamName;

    @Column(name = "match_id")
    @NotNull
    private int matchId;

    @Column(name = "fantasy_score")
    @NotNull
    private float fantasyScore;

    public int getLeaderboardId() {
        return leaderboardId;
    }

    public void setLeaderboardId(int leaderboardId) {
        this.leaderboardId = leaderboardId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public float getFantasyScore() {
        return fantasyScore;
    }

    public void setFantasyScore(float fantasyScore) {
        this.fantasyScore = fantasyScore;
    }

}
