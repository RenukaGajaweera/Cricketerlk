package com.cricketerlk.models.fantasyleague;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Supun on 6/5/2017.
 */

@Transactional
@Repository
public class FantasyLeagueDao implements IFantasyLeagueDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public FantasyLeague getFantasyLeague(String playerId, int matchId) throws NoResultException {
        String hql = "FROM FantasyLeague as fantasy_league WHERE fantasy_league.matchId=:matchId and fantasy_league.userId=:playerId ORDER BY fantasy_league.leagueId";
        return (FantasyLeague) entityManager.createQuery(hql)
                .setParameter("matchId", matchId)
                .setParameter("playerId", playerId)
                .getSingleResult();
    }

    @Override
    public List<FantasyLeague> getFantasyLeagueByMatch(int matchId) {
        String hql = "FROM FantasyLeague as fantasy_league WHERE fantasy_league.matchId=:matchId ORDER BY fantasy_league.leagueId";
        return (List<FantasyLeague>) entityManager.createQuery(hql)
                .setParameter("matchId", matchId)
                .getResultList();
    }

    @Override
    public List<FantasyLeague> getFantasyLeagueByPlayer(int playerId) {
        String hql = "FROM FantasyLeague as fantasy_league WHERE fantasy_league.userId=:playerId ORDER BY fantasy_league.squad_id";
        return (List<FantasyLeague>) entityManager.createQuery(hql)
                .setParameter("playerId", playerId)
                .getResultList();
    }

    @Override
    public void addFantasyLeague(FantasyLeague fantasyLeague) {
        entityManager.persist(fantasyLeague);
        entityManager.flush();
        entityManager.clear();
    }

    @Override
    public FantasyLeague updateFantasyLeague(FantasyLeague fantasyLeague) {
        return entityManager.merge(fantasyLeague);
    }
}
