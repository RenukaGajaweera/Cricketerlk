package com.cricketerlk.services;

import com.cricketerlk.models.playerscorecard.PlayerScoreCard;
import com.cricketerlk.models.playerscorecard.PlayerScoreCardList;

import java.util.List;

/**
 * Created by Supun on 6/4/2017.
 */
public interface IScoreCardService {

    public  boolean createMatchScoreCards(int matchId);

    public PlayerScoreCardList retrieveDetailedScoreCards(int matchId);

    List<PlayerScoreCard> getScoreCardsByMatchId(int matchId);

    boolean addScoreCardBatch(List<PlayerScoreCard> scoreCardList);

    PlayerScoreCard updateScoreCard(PlayerScoreCard scoreCard);

    List<PlayerScoreCard> updateScoreCardBatch(List<PlayerScoreCard> scoreCardList);

}
