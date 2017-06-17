package com.cricketerlk.models.matchschedule;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * Created by Supun on 6/2/2017.
 */

@Entity
@Table(name = "match_schedules")
public class MatchSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "schedule_id")
    private int scheduleId;

    @Column(name = "match_date")
    @NotNull
    private Date matchDate;

    @Column(name = "team_one")
    @NotNull
    private int teamOne;

    @Column(name = "team_two")
    @NotNull
    private int teamTwo;

    @Column(name = "match_group")
    @NotBlank
    private String matchGroup;

    @Column(name = "match_number")
    @NotNull
    private int matchNumber;

    @Column(name = "current_status")
    @NotNull
    private int currentStatus;

    @Column(name = "match_result")
    private int matchResult;

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    public int getTeamOne() {
        return teamOne;
    }

    public void setTeamOne(int teamOne) {
        this.teamOne = teamOne;
    }

    public int getTeamTwo() {
        return teamTwo;
    }

    public void setTeamTwo(int teamTwo) {
        this.teamTwo = teamTwo;
    }

    public String getMatchGroup() {
        return matchGroup;
    }

    public void setMatchGroup(String matchGroup) {
        this.matchGroup = matchGroup;
    }

    public int getMatchNumber() {
        return matchNumber;
    }

    public void setMatchNumber(int matchNumber) {
        this.matchNumber = matchNumber;
    }

    public int getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(int currentStatus) {
        this.currentStatus = currentStatus;
    }

    public int getMatchResult() {
        return matchResult;
    }

    public void setMatchResult(int matchResult) {
        this.matchResult = matchResult;
    }
}
