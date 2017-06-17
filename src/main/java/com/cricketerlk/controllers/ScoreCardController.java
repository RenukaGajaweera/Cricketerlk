package com.cricketerlk.controllers;

import com.cricketerlk.common.ClkResponseMessage;
import com.cricketerlk.models.playerscorecard.PlayerScoreCard;
import com.cricketerlk.models.playerscorecard.PlayerScoreCardList;
import com.cricketerlk.services.IScoreCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Supun on 6/4/2017.
 */

@Controller
@RequestMapping("/score-card")
public class ScoreCardController {

    @Autowired
    IScoreCardService scoreCardService;

    @CrossOrigin(origins = "*", allowCredentials = "true")
    @RequestMapping(method = RequestMethod.GET, value = "retrieveList/{matchId}")
    public ResponseEntity<ClkResponseMessage> getMatchScoreCard(@PathVariable("matchId") int matchId) {
        ClkResponseMessage<PlayerScoreCardList> responseMessage;
        PlayerScoreCardList detailedScoreCardList = scoreCardService.retrieveDetailedScoreCards(matchId);
        if (detailedScoreCardList.getPlayerList().isEmpty() && !detailedScoreCardList.getScoreCardList().isEmpty()) {
            responseMessage = new ClkResponseMessage<PlayerScoreCardList>(new PlayerScoreCardList(), "FAILED TO RETRIEVE MATCH SCORE CARDS");
            return new ResponseEntity<ClkResponseMessage>(responseMessage, HttpStatus.BAD_REQUEST);
        }
        responseMessage = new ClkResponseMessage<PlayerScoreCardList>(detailedScoreCardList, "SUCCESS");
        return new ResponseEntity<ClkResponseMessage>(responseMessage, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*", allowCredentials = "true")
    @RequestMapping(method = RequestMethod.GET, value = "storeList/{matchId}")
    public ResponseEntity<ClkResponseMessage> createMatchScoreCard(@PathVariable("matchId") int matchId) {
        ClkResponseMessage<Boolean> responseMessage;
        Boolean flag = scoreCardService.createMatchScoreCards(matchId);
        if (!flag) {
            responseMessage = new ClkResponseMessage<Boolean>(flag, "FAILED TO CREATE MATCH SCORE CARDS");
            return new ResponseEntity<ClkResponseMessage>(responseMessage, HttpStatus.BAD_REQUEST);
        }
        responseMessage = new ClkResponseMessage<Boolean>(flag, "SUCCESS");
        return new ResponseEntity<ClkResponseMessage>(responseMessage, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*", allowCredentials = "true")
    @RequestMapping(method = RequestMethod.POST, value = "updateList/")
    public ResponseEntity<ClkResponseMessage> updateMatchScoreCard(@RequestBody PlayerScoreCardList scoreCardList) {
        ClkResponseMessage<PlayerScoreCardList> responseMessage;
        List<PlayerScoreCard> scoreCards = scoreCardService.updateScoreCardBatch(scoreCardList.getScoreCardList());
        if (scoreCards.isEmpty()) {
            responseMessage = new ClkResponseMessage<PlayerScoreCardList>(new PlayerScoreCardList(), "FAILED TO CREATE MATCH SCORE CARDS");
            return new ResponseEntity<ClkResponseMessage>(responseMessage, HttpStatus.BAD_REQUEST);
        }
        scoreCardList.setScoreCardList(scoreCards);
        responseMessage = new ClkResponseMessage<PlayerScoreCardList>(scoreCardList, "SUCCESS");
        return new ResponseEntity<ClkResponseMessage>(responseMessage, HttpStatus.OK);
    }

}
