package com.cricketerlk.services;

import com.cricketerlk.models.fantasyleague.*;
import com.cricketerlk.models.leaderboard.ILeaderBoardDao;
import com.cricketerlk.models.matchschedule.MatchSchedule;
import com.cricketerlk.models.playerscorecard.IPlayerScoreCardDao;
import com.cricketerlk.models.playerscorecard.PlayerScoreCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Supun on 6/5/2017.
 */

@Service
public class FantasyLeagueService implements IFantasyLeagueService {

    @Autowired
    IFantasyLeagueDao fantasyLeagueDao;
    @Autowired
    IFantasyLeagueSquadDao fantasyLeagueSquadDao;
    @Autowired
    IPlayerScoreCardDao playerScoreCardDao;
    @Autowired
    IMatchScheduleService matchScheduleService;

    @Autowired
    IScoreCardService scoreCardService;

    @Override
    public DetailedFantasyLeague retrieveMatchLeagueDetails(int matchId, String userId) {
        DetailedFantasyLeague detailedFantasyLeague = new DetailedFantasyLeague();
        detailedFantasyLeague.setLeagueMetaDate(fantasyLeagueDao.getFantasyLeague(userId, matchId));
        detailedFantasyLeague.setLeagueSquad(fantasyLeagueSquadDao.getSquadByPlayId(detailedFantasyLeague.getLeagueMetaDate().getLeagueId()));
        return detailedFantasyLeague;
    }

    @Override
    public List<DetailedFantasyLeague> retrievePlayerLeagueDetails(String userId) {
        return null;
    }

    @Override
    public synchronized boolean addStorePlayerFantasyLeagueTeam(DetailedFantasyLeague fantasyTeam) {
        /** At this point fantasy league exist only need to add squad info and update fantasy league*/
        fantasyLeagueDao.updateFantasyLeague(fantasyTeam.getLeagueMetaDate());
        fantasyLeagueSquadDao.addFantasyLeagueSquad(fantasyTeam.getLeagueSquad());
        return true;
    }

    @Override
    public DetailedFantasyLeague updatePlayerFantasyLeagueTeam(DetailedFantasyLeague fantasyTeam) {
        DetailedFantasyLeague updatedLeagueInformation = new DetailedFantasyLeague();
        updatedLeagueInformation.setLeagueMetaDate(fantasyLeagueDao.updateFantasyLeague(fantasyTeam.getLeagueMetaDate()));
        updatedLeagueInformation.setLeagueSquad(fantasyLeagueSquadDao.updateSquad(fantasyTeam.getLeagueSquad()));
        return updatedLeagueInformation;
    }

    @Override
    public boolean fantasyLeagueExist(int matchId, String userId) {
        try {
            FantasyLeague fantasyLeague = fantasyLeagueDao.getFantasyLeague(userId, matchId);
        } catch (NoResultException ex) {
            return false;
        }
        return true;
    }

    @Override
    public synchronized boolean createFantasyLeague( String userId, int matchId, int structure) {
        FantasyLeague tempLeague = new FantasyLeague();
        tempLeague.setMatchId(matchId);
        tempLeague.setUserId(userId);
        tempLeague.setStructureCode(structure);
        fantasyLeagueDao.addFantasyLeague(tempLeague);
        return true;
    }

    @Override
    public FantasyLeague retrieveFantasyLeague(String userId, int matchId) {
        return fantasyLeagueDao.getFantasyLeague(userId, matchId);
    }


    @Override
    public synchronized boolean updateFantasyLeagueScores(int matchId) {
        MatchSchedule matchSchedule = matchScheduleService.getMatchById(matchId);
        List<PlayerScoreCard> scoreCardList = scoreCardService.getScoreCardsByMatchId(matchId);
        List<FantasyLeague> fantasyLeagues = fantasyLeagueDao.getFantasyLeagueByMatch(matchId);
        for (FantasyLeague fantasyLeague : fantasyLeagues) {
            fantasyLeague.setExtra(0);
            fantasyLeague.setBowlingScore(0);
            fantasyLeague.setBattingScore(0);
            fantasyLeague.setFieldingScore(0);
            List<FantasyLeagueSquad> fantasyLeagueSquadList = fantasyLeagueSquadDao.getSquadByPlayId(fantasyLeague.getLeagueId());
            for (FantasyLeagueSquad squadElement : fantasyLeagueSquadList) {
                for (PlayerScoreCard scoreCard : scoreCardList) {
                    int scoreCardPlayerId = scoreCard.getPlayerId();
                    if (squadElement.getPlayerId() == scoreCard.getPlayerId()) {
                        float multiplier;
                        if (fantasyLeague.getCaptain() == scoreCardPlayerId) {
                            multiplier = 2;
                        } else if (fantasyLeague.getViceCaptain() == scoreCard.getPlayerId()) {
                            multiplier = 1.5F;
                        } else {
                            multiplier = 1;
                        }
                        fantasyLeague.setBattingScore(fantasyLeague.getBattingScore() + (scoreCard.getBattingScore() * multiplier));
                        fantasyLeague.setBowlingScore(fantasyLeague.getBowlingScore() + (scoreCard.getBowlingScore() * multiplier));
                        fantasyLeague.setFieldingScore(fantasyLeague.getFieldingScore() + (scoreCard.getFieldingScore() * multiplier));
                        fantasyLeague.setExtra(fantasyLeague.getExtra() + (scoreCard.getExtra() * multiplier));
                        if(scoreCard.isMom()) {
                            fantasyLeague.setExtra(fantasyLeague.getExtra() + (50 * multiplier));
                        }
                        break;
                    }
                }
            }
            fantasyLeagueDao.updateFantasyLeague(fantasyLeague);
        }
        return true;
    }

    @Override
    public void updateFantasyLeagueScoresNullEntries(int matchId) {

    }
}
