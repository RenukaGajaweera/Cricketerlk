package com.cricketerlk.services;

import com.cricketerlk.models.matchschedule.MatchSchedule;
import com.cricketerlk.models.player.Player;
import com.cricketerlk.models.playerscorecard.IPlayerScoreCardDao;
import com.cricketerlk.models.playerscorecard.PlayerScoreCard;
import com.cricketerlk.models.playerscorecard.PlayerScoreCardList;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Supun on 6/4/2017.
 */

@Service
public class ScoreCardService implements IScoreCardService {

    private static Logger logger = Logger.getLogger(ScoreCardService.class);

    @Autowired
    private IPlayerScoreCardDao playerScoreCardDao;
    @Autowired
    private IPlayerService playerService;
    @Autowired
    private IMatchScheduleService matchScheduleService;

    public synchronized boolean createMatchScoreCards(int matchId) {
        List<PlayerScoreCard> playerScoreCardList = new ArrayList<PlayerScoreCard>();
        MatchSchedule matchSchedule = matchScheduleService.getMatchById(matchId);
        List<Player> playerList = playerService.getAllPlayersByCountryCode(matchSchedule.getTeamOne());
        playerList.addAll(playerService.getAllPlayersByCountryCode(matchSchedule.getTeamTwo()));
        PlayerScoreCard tempScoreCard;
        for (Player player : playerList) {
            tempScoreCard = new PlayerScoreCard();
            tempScoreCard.setMatchId(matchId);
            tempScoreCard.setPlayerId(player.getPlayerId());
            playerScoreCardList.add(tempScoreCard);
        }
        try {
            playerScoreCardDao.addScoreCardBatch(playerScoreCardList);
        } catch (Exception ex) {
            logger.error(ex);
            return false;
        }
        return true;
    }

    public synchronized PlayerScoreCardList retrieveDetailedScoreCards(int matchId) {
        PlayerScoreCardList detailedCard = new PlayerScoreCardList();
        detailedCard.setScoreCardList(playerScoreCardDao.getScoreCardByMatch(matchId));
        MatchSchedule matchSchedule = matchScheduleService.getMatchById(matchId);
        List<Player> playerList = playerService.getAllPlayersByCountryCode(matchSchedule.getTeamOne());
        playerList.addAll(playerService.getAllPlayersByCountryCode(matchSchedule.getTeamTwo()));
        detailedCard.setPlayerList(playerList);
        return detailedCard;
    }

    @Override
    public List<PlayerScoreCard> getScoreCardsByMatchId(int matchId) {
        return playerScoreCardDao.getScoreCardByMatch(matchId);
    }

    @Override
    public synchronized boolean addScoreCardBatch(List<PlayerScoreCard> scoreCardList) {
        playerScoreCardDao.addScoreCardBatch(scoreCardList);
        return true;
    }

    @Override
    public PlayerScoreCard updateScoreCard(PlayerScoreCard scoreCard) {
        return playerScoreCardDao.updateScoreCard(scoreCard);
    }

    @Override
    public List<PlayerScoreCard> updateScoreCardBatch(List<PlayerScoreCard> scoreCardList) {
        try {
            return playerScoreCardDao.updateScoreCardBatch(scoreCardList);
        } catch (Exception ex) {
            logger.error(ex);
        }
        return new ArrayList<PlayerScoreCard>();
    }
}
