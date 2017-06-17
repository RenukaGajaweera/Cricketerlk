package com.cricketerlk.models.leaderboard;

import java.util.List;

/**
 * Created by Supun on 6/8/2017.
 */
public interface ILeaderBoardDao {

    public List<LeaderBoardResult> getLeaderBoardByMatchId(int matchId);

    public List<LeaderBoardResult> getLeaderBoardByUserId(String userId);

    public List<LeaderBoardResult> getLeaderBoardFinalResult();

    public List<LeaderBoardResult> updateLeaderBoard(List<LeaderBoardResult> leaderBoardResultList);

    public void addNewEntry(LeaderBoardResult leaderBoardResult);

    public void addNewEntryBatch(List<LeaderBoardResult> leaderBoardResultList);

    public void deleteExistingLeaderBoardResults(List<LeaderBoardResult> leaderBoardResultList);

}
