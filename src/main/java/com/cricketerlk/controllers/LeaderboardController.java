package com.cricketerlk.controllers;

import com.cricketerlk.common.ClkResponseMessage;
import com.cricketerlk.models.leaderboard.LeaderBoardResult;
import com.cricketerlk.services.ILeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Supun on 6/8/2017.
 */

@Controller
@RequestMapping("/leaderboard")
public class LeaderboardController {

    @Autowired
    ILeaderboardService leaderboardService;

    @CrossOrigin(origins = "*", allowCredentials = "true")
    @RequestMapping(method = RequestMethod.GET, value = "/calculate/{matchId}")
    public ResponseEntity<ClkResponseMessage> calculateMatchLeaderBoardScores(@PathVariable("matchId") int matchId) {
        ClkResponseMessage<Boolean> responseMessage;
        boolean flag = leaderboardService.calculateScores(matchId);
        if(!flag) {
            responseMessage = new ClkResponseMessage<Boolean>(flag, "FAILED UPDATE");
            return new ResponseEntity<ClkResponseMessage>(responseMessage, HttpStatus.OK);
        }
        responseMessage = new ClkResponseMessage<Boolean>(flag, "SUCCESS");
        return new ResponseEntity<ClkResponseMessage>(responseMessage, HttpStatus.OK);

    }

    @CrossOrigin(origins = "*", allowCredentials = "true")
    @RequestMapping(method = RequestMethod.GET, value = "/final")
    public ResponseEntity<ClkResponseMessage> getLeaderBoardScores() {
        ClkResponseMessage<List<LeaderBoardResult>> responseMessage;
        List<LeaderBoardResult> totalScoreList = leaderboardService.getLeaderBoard();
        if(totalScoreList.isEmpty()) {
            responseMessage = new ClkResponseMessage<List<LeaderBoardResult>>(new ArrayList<LeaderBoardResult>(), "DOES NOTS EXIST");
            return new ResponseEntity<ClkResponseMessage>(responseMessage, HttpStatus.OK);
        }
        responseMessage = new ClkResponseMessage<List<LeaderBoardResult>>(totalScoreList, "EXISTS");
        return new ResponseEntity<ClkResponseMessage>(responseMessage, HttpStatus.OK);

    }


}
