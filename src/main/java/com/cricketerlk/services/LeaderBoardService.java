package com.cricketerlk.services;


import com.cricketerlk.models.fantasyleague.FantasyLeague;
import com.cricketerlk.models.fantasyleague.IFantasyLeagueDao;
import com.cricketerlk.models.fantasyleague.IFantasyLeagueSquadDao;
import com.cricketerlk.models.leaderboard.ILeaderBoardDao;
import com.cricketerlk.models.leaderboard.LeaderBoardResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Supun on 6/8/2017.
 */
@Service
public class LeaderBoardService implements ILeaderboardService {

    @Autowired
    ILeaderBoardDao leaderBoardDao;

    @Autowired
    IFantasyLeagueDao fantasyLeagueDao;

    @Override
    public List<LeaderBoardResult> getLeaderBoard() {
        return leaderBoardDao.getLeaderBoardFinalResult();
    }

    @Override
    public List<LeaderBoardResult> getMatchResultsListByUser(String userId) {
        return leaderBoardDao.getLeaderBoardByUserId(userId);
    }

    @Override
    public List<LeaderBoardResult> getUserResultsListByMatch(int matchId) {
        return leaderBoardDao.getLeaderBoardByMatchId(matchId);
    }

    @Override
    public synchronized boolean addNewLeaderBoardEntry(LeaderBoardResult leaderBoardResult) {
        leaderBoardDao.addNewEntry(leaderBoardResult);
        return true;
    }

    @Override
    public synchronized boolean addNewLeaderBoardEntryBatch(List<LeaderBoardResult> leaderBoardResult) {
        leaderBoardDao.addNewEntryBatch(leaderBoardResult);
        return true;
    }

    @Override
    public List<LeaderBoardResult> updateAllLeaderBoardResults(List<LeaderBoardResult> leaderBoardResultList) {
        return leaderBoardDao.updateLeaderBoard(leaderBoardResultList);
    }

    @Override
    public synchronized boolean calculateScores(int matchId) {
        List<FantasyLeague> fantasyLeagueList = fantasyLeagueDao.getFantasyLeagueByMatch(matchId);
        List<LeaderBoardResult> matchResultList = leaderBoardDao.getLeaderBoardByMatchId(matchId);
        if (matchResultList.isEmpty()) {
            List<LeaderBoardResult> previousMatchLeaderList = leaderBoardDao.getLeaderBoardByMatchId(matchId - 1);
            List<LeaderBoardResult> newMatchLeaderList = new ArrayList<LeaderBoardResult>();
            List<LeaderBoardResult> notPlayedList = new ArrayList<LeaderBoardResult>();
            float smallestSum = 10000.0f;
            for (LeaderBoardResult leaderBoardResult : previousMatchLeaderList) {
                boolean hasPlayedMatch = false;
                for (FantasyLeague fantasyLeague : fantasyLeagueList) {
                    LeaderBoardResult tempLeaderBoardResult = new LeaderBoardResult();
                    float sum = fantasyLeague.getBattingScore() + fantasyLeague.getBowlingScore() + fantasyLeague.getFieldingScore() + fantasyLeague.getExtra();
                    if (sum < smallestSum && sum > 0.0) {
                        smallestSum = sum;
                    }
                    if (fantasyLeague.getUserId().equals(leaderBoardResult.getUserId())) {
                        if (sum > 0.0f) {
                            hasPlayedMatch = true;
                            tempLeaderBoardResult.setMatchId(fantasyLeague.getMatchId());
                            tempLeaderBoardResult.setUserId(fantasyLeague.getUserId());
                            tempLeaderBoardResult.setUserEmail(leaderBoardResult.getUserEmail());
                            tempLeaderBoardResult.setFantasyScore(sum);
                            tempLeaderBoardResult.setTeamName(leaderBoardResult.getTeamName());
                            newMatchLeaderList.add(tempLeaderBoardResult);
                            break;
                        }
                    }
                }
                if (!hasPlayedMatch) {
                    LeaderBoardResult missingLeaderBoardResult = new LeaderBoardResult();
                    missingLeaderBoardResult.setMatchId(matchId);
                    missingLeaderBoardResult.setTeamName(leaderBoardResult.getTeamName());
                    missingLeaderBoardResult.setUserId(leaderBoardResult.getUserId());
                    missingLeaderBoardResult.setUserEmail(leaderBoardResult.getUserEmail());
                    notPlayedList.add(missingLeaderBoardResult);
                }
            }
            smallestSum -= 100;
            for (LeaderBoardResult missingResult : notPlayedList) {
                missingResult.setFantasyScore(smallestSum);
            }
            newMatchLeaderList.addAll(notPlayedList);
            leaderBoardDao.addNewEntryBatch(newMatchLeaderList);
        } else {
            boolean flag = deleteLeaderBoardResultSet(matchResultList);
            if (flag) {
                calculateScores(matchId);
            } else {
                return false;
            }
        }
        return true;
    }

    @Override
    public synchronized boolean deleteLeaderBoardResultSet(List<LeaderBoardResult> leaderBoardResultList) {
        leaderBoardDao.deleteExistingLeaderBoardResults(leaderBoardResultList);
        return true;
    }
}
