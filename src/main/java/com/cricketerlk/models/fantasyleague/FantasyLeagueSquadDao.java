package com.cricketerlk.models.fantasyleague;

import com.cricketerlk.models.playerscorecard.PlayerScoreCard;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Supun on 6/5/2017.
 */

@Transactional
@Repository
public class FantasyLeagueSquadDao implements IFantasyLeagueSquadDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<FantasyLeagueSquad> getSquadByPlayId(int playId) {
        String hql = "FROM FantasyLeagueSquad as fantasy_league_squad WHERE fantasy_league_squad.leagueId=:playId ORDER BY fantasy_league_squad.squadId";
        return (List<FantasyLeagueSquad>) entityManager.createQuery(hql)
                .setParameter("playId", playId)
                .getResultList();
    }

    @Override
    public void addFantasyLeagueSquad(List<FantasyLeagueSquad> squad) {
        entityManager.flush();
        for (FantasyLeagueSquad squadElement : squad)
        {
            entityManager.persist(squadElement);;
        }
        entityManager.flush();
        entityManager.clear();
    }

    @Override
    public List<FantasyLeagueSquad> updateSquad(List<FantasyLeagueSquad> squad) {
        List<FantasyLeagueSquad> tempSquadList = new ArrayList<FantasyLeagueSquad>();
        entityManager.flush();
        for (FantasyLeagueSquad squadElement : squad)
        {
            tempSquadList.add(entityManager.merge(squadElement));
        }
        entityManager.flush();
        entityManager.clear();
        return tempSquadList;
    }
}
