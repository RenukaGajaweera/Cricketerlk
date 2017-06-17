package com.cricketerlk.services;

import com.cricketerlk.models.matchschedule.MatchSchedule;

import java.sql.Date;
import java.util.List;

/**
 * Created by Supun on 6/2/2017.
 */
public interface IMatchScheduleService {

    List<MatchSchedule> getAllMatchSchedules();

    MatchSchedule getMatchById(int matchId);

    List<MatchSchedule> getMatchByDate(Date matchDate);

    List<MatchSchedule> getOnGoingMatchDetails(Date fromMatchDate, Date toMatchDate);

    List<MatchSchedule> getMatchByStatus(int status);

    boolean addMatch(MatchSchedule match);

    MatchSchedule updateMatch(MatchSchedule match);
}
