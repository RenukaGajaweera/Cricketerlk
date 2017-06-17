package com.cricketerlk.services;

import com.cricketerlk.models.matchschedule.IMatchScheduleDao;
import com.cricketerlk.models.matchschedule.MatchSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

/**
 * Created by Supun on 6/2/2017.
 */
@Service
public class MatchScheduleService implements IMatchScheduleService {

    @Autowired
    private IMatchScheduleDao matchScheduleDao;

    @Override
    public List<MatchSchedule> getAllMatchSchedules() {
        return matchScheduleDao.getAllMatches();
    }

    @Override
    public MatchSchedule getMatchById(int matchId) {
        return matchScheduleDao.getMatchById(matchId);
    }

    @Override
    public List<MatchSchedule> getMatchByDate(Date matchDate) {
        return matchScheduleDao.getMatchByDate(matchDate);
    }

    @Override
    public List<MatchSchedule> getOnGoingMatchDetails(Date fromMatchDate, Date toMatchDate) {
        return matchScheduleDao.getOnGoingMatchDetails(fromMatchDate, toMatchDate);
    }

    @Override
    public List<MatchSchedule> getMatchByStatus(int status) {
        return matchScheduleDao.getMatchByStatus(status);
    }

    @Override
    public synchronized boolean addMatch(MatchSchedule match) {
        matchScheduleDao.addMatch(match);
        return true;
    }

    @Override
    public MatchSchedule updateMatch(MatchSchedule match) {
        return matchScheduleDao.updateMatch(match);
    }
}
