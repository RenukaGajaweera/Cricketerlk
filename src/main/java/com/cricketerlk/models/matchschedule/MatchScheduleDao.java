package com.cricketerlk.models.matchschedule;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;

/**
 * Created by Supun on 6/2/2017.
 */

@Transactional
@Repository
public class MatchScheduleDao implements IMatchScheduleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<MatchSchedule> getAllMatches() {
        String hql = "FROM MatchSchedule as match_schedules ORDER BY match_schedules.matchDate";
        return (List<MatchSchedule>) entityManager.createQuery(hql).getResultList();
    }

    @Override
    public List<MatchSchedule> getMatchByDate(Date matchDate) {
        String hql = "FROM MatchSchedule as match_schedules WHERE match_schedules.matchDate=:matchDate ORDER BY match_schedules.matchDate";
        return (List<MatchSchedule>) entityManager.createQuery(hql)
                .setParameter("matchDate", matchDate)
                .getResultList();
    }

    @Override
    public List<MatchSchedule> getMatchByStatus(int matchStatus) {
        String hql = "FROM MatchSchedule as match_schedules WHERE match_schedules.currentStatus=:matchStatus ORDER BY match_schedules.matchDate";
        return (List<MatchSchedule>) entityManager.createQuery(hql)
                .setParameter("matchStatus", matchStatus)
                .getResultList();
    }

    @Override
    public List<MatchSchedule> getOnGoingMatchDetails(Date fromMatchDate, Date toMatchDate) {
        String hql = "FROM MatchSchedule as match_schedules WHERE match_schedules.matchDate between :fromMatchDate and :toMatchDate ORDER BY match_schedules.matchDate";
        return (List<MatchSchedule>) entityManager.createQuery(hql)
                .setParameter("fromMatchDate", fromMatchDate)
                .setParameter("toMatchDate", toMatchDate)
                .getResultList();
    }

    @Override
    public MatchSchedule getMatchById(int matchId) {
        return entityManager.find(MatchSchedule.class, matchId);
    }

    @Override
    public void addMatch(MatchSchedule match) {
        entityManager.persist(match);
    }

    @Override
    public MatchSchedule updateMatch(MatchSchedule match) {
        return entityManager.merge(match);
    }
}
