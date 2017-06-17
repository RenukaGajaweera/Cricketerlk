package com.cricketerlk.models.matchschedule;

import java.sql.Date;
import java.util.List;

/**
 * Created by Supun on 6/2/2017.
 */
public interface IMatchScheduleDao {

    List<MatchSchedule> getAllMatches();

    List<MatchSchedule> getMatchByDate(Date matchDate);

    List<MatchSchedule> getMatchByStatus(int matchStatus);

    List<MatchSchedule> getOnGoingMatchDetails(Date fromMatchDate, Date toMatchDate);

    MatchSchedule getMatchById(int matchId);

    void addMatch(MatchSchedule match);

    MatchSchedule updateMatch(MatchSchedule match);

}
