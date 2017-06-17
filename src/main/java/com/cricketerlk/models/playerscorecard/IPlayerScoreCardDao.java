package com.cricketerlk.models.playerscorecard;

import java.util.List;

/**
 * Created by Supun on 6/4/2017.
 */

public interface IPlayerScoreCardDao {

    List<PlayerScoreCard> getScoreCardByMatch(int matchId);

    void addScoreCard(PlayerScoreCard scoreCard);

    void addScoreCardBatch(List<PlayerScoreCard> scoreCardList);

    PlayerScoreCard updateScoreCard(PlayerScoreCard scoreCard);

    List<PlayerScoreCard> updateScoreCardBatch(List<PlayerScoreCard> scoreCardList);

}
