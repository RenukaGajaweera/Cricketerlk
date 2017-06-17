package com.cricketerlk.models.playerscorecard;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Supun on 6/4/2017.
 */

@Transactional
@Repository
public class PlayerScoreCardDao implements IPlayerScoreCardDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<PlayerScoreCard> getScoreCardByMatch(int matchId) {
        String hql = "FROM PlayerScoreCard as player_match_score_card WHERE player_match_score_card.matchId=:matchId ORDER BY player_match_score_card.scoreCardId";
        return (List<PlayerScoreCard>) entityManager.createQuery(hql)
                .setParameter("matchId", matchId)
                .getResultList();
    }

    @Override
    public void addScoreCard(PlayerScoreCard scoreCard) {
        entityManager.persist(scoreCard);
    }

    @Override
    public void addScoreCardBatch(List<PlayerScoreCard> scoreCardList) {
        entityManager.flush();
        for (PlayerScoreCard scoreCard : scoreCardList)
        {
            entityManager.persist(scoreCard);;
        }
        entityManager.flush();
        entityManager.clear();
    }

    @Override
    public PlayerScoreCard updateScoreCard(PlayerScoreCard scoreCard) {
        return entityManager.merge(scoreCard);
    }

    @Override
    public List<PlayerScoreCard> updateScoreCardBatch(List<PlayerScoreCard> scoreCardList) {
        List<PlayerScoreCard> tempScoreCardList = new ArrayList<PlayerScoreCard>();
        entityManager.flush();
        for (PlayerScoreCard scoreCard : scoreCardList)
        {
            tempScoreCardList.add(entityManager.merge(scoreCard));
        }
        entityManager.flush();
        entityManager.clear();
        return tempScoreCardList;
    }
}
