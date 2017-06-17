package com.cricketerlk.services;

import com.cricketerlk.models.leaderboard.LeaderBoardResult;

import java.util.List;

/**
 * Created by Supun on 6/8/2017.
 */

public interface ILeaderboardService {

    public List<LeaderBoardResult> getLeaderBoard();

    public List<LeaderBoardResult> getMatchResultsListByUser(String userId);

    public List<LeaderBoardResult> getUserResultsListByMatch(int matchId);

    public boolean addNewLeaderBoardEntry(LeaderBoardResult leaderBoardResult);

    public boolean addNewLeaderBoardEntryBatch(List<LeaderBoardResult> leaderBoardResult);

    public List<LeaderBoardResult> updateAllLeaderBoardResults(List<LeaderBoardResult> leaderBoardResultList);

    public boolean calculateScores(int matchId);

    public boolean deleteLeaderBoardResultSet(List<LeaderBoardResult> leaderBoardResultList);

}
