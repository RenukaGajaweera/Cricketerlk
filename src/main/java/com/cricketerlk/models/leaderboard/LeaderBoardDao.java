package com.cricketerlk.models.leaderboard;

import com.cricketerlk.models.matchschedule.MatchSchedule;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Supun on 6/8/2017.
 */

@Transactional
@Repository
public class LeaderBoardDao implements ILeaderBoardDao {

    @PersistenceContext
    private EntityManager entityManager;

//    @Override
//    public List<LeaderBoardResult> getLeaderBoard() {
//        String hql = "FROM LeaderBoardResult as leaderboard ORDER BY leaderboard.fantasyScore DESC";
//        return (List<LeaderBoardResult>) entityManager.createQuery(hql).getResultList();
//    }

    @Override
    public List<LeaderBoardResult> getLeaderBoardByMatchId(int matchId) {
        String hql = "FROM LeaderBoardResult as leaderboard WHERE leaderboard.matchId=:matchId ORDER BY leaderboard.fantasyScore DESC";
        return (List<LeaderBoardResult>) entityManager.createQuery(hql)
                .setParameter("matchId", matchId)
                .getResultList();
    }

    @Override
    public List<LeaderBoardResult> getLeaderBoardByUserId(String userId) {
        String hql = "FROM LeaderBoardResult as leaderboard WHERE leaderboard.userId=:userId ORDER BY leaderboard.fantasyScore DESC";
        return (List<LeaderBoardResult>) entityManager.createQuery(hql)
                .setParameter("userId", userId)
                .getResultList();
    }

//    SELECT user_id, user_email, team_name, 0, SUM(fantasy_score) fantasy_score_sum FROM leaderboard GROUP BY user_id, team_name ORDER BY fantasy_score_sum;
    @Override
    public List<LeaderBoardResult> getLeaderBoardFinalResult() {
        String hql = "SELECT leaderboard.userId, leaderboard.teamName, sum(leaderboard.fantasyScore) FROM LeaderBoardResult as leaderboard GROUP BY leaderboard.userId, leaderboard.teamName";
        return (List<LeaderBoardResult>) entityManager.createQuery(hql)
                .getResultList();
    }

    @Override
    public List<LeaderBoardResult> updateLeaderBoard(List<LeaderBoardResult> leaderBoardResultList) {
        List<LeaderBoardResult> tempLeaderBoardList = new ArrayList<LeaderBoardResult>();
        entityManager.flush();
        for (LeaderBoardResult result : leaderBoardResultList) {
            tempLeaderBoardList.add(entityManager.merge(result));
        }
        entityManager.flush();
        entityManager.clear();
        return tempLeaderBoardList;
    }

    @Override
    public void addNewEntry(LeaderBoardResult leaderBoardResult) {
        entityManager.persist(leaderBoardResult);
    }

    @Override
    public void addNewEntryBatch(List<LeaderBoardResult> leaderBoardResultList) {
        entityManager.flush();
        for (LeaderBoardResult result : leaderBoardResultList) {
            entityManager.persist(result);
        }
        entityManager.flush();
        entityManager.clear();
    }

    @Override
    public void deleteExistingLeaderBoardResults(List<LeaderBoardResult> leaderBoardResultList) {
        entityManager.flush();
        for (LeaderBoardResult result : leaderBoardResultList) {
            entityManager.remove(result);
        }
        entityManager.flush();
        entityManager.clear();
    }

}
