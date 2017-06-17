package com.cricketerlk.controllers;

import com.cricketerlk.common.ClkResponseMessage;
import com.cricketerlk.models.fantasyleague.DetailedFantasyLeague;
import com.cricketerlk.models.fantasyleague.FantasyLeague;
import com.cricketerlk.models.matchschedule.MatchSchedule;
import com.cricketerlk.services.IFantasyLeagueService;
import com.cricketerlk.services.IMatchScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Supun on 6/6/2017.
 */

@Controller
@RequestMapping("/fantasy")
public class FantasyLeagueController {

    @Autowired
    IFantasyLeagueService fantasyLeagueService;

    @Autowired
    IMatchScheduleService matchScheduleService;

    @CrossOrigin(origins = "*", allowCredentials = "true")
    @RequestMapping(method = RequestMethod.GET, value = "/exist/{userId}/{matchId}")
    public ResponseEntity<ClkResponseMessage> fantasyLeagueExist(@PathVariable("userId") String userId, @PathVariable("matchId") int matchId) {
        ClkResponseMessage<Boolean> responseMessage;
        boolean flag = fantasyLeagueService.fantasyLeagueExist(matchId, userId);
        if (!flag) {
            responseMessage = new ClkResponseMessage<Boolean>(flag, "DOES NOTS EXIST");
            return new ResponseEntity<ClkResponseMessage>(responseMessage, HttpStatus.OK);
        }
        responseMessage = new ClkResponseMessage<Boolean>(flag, "EXISTS");
        return new ResponseEntity<ClkResponseMessage>(responseMessage, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*", allowCredentials = "true")
    @RequestMapping(method = RequestMethod.GET, value = "retrieve/{userId}/{matchId}")
    public ResponseEntity<ClkResponseMessage> retrieveFantasyLeagueDetails(@PathVariable("userId") String userId, @PathVariable("matchId") int matchId) {
        ClkResponseMessage<DetailedFantasyLeague> responseMessage;
        DetailedFantasyLeague detailedFantasyLeague = fantasyLeagueService.retrieveMatchLeagueDetails(matchId, userId);
        if (detailedFantasyLeague.getLeagueMetaDate() == null) {
            responseMessage = new ClkResponseMessage<DetailedFantasyLeague>(new DetailedFantasyLeague(), "DOES NOTS EXIST");
            return new ResponseEntity<ClkResponseMessage>(responseMessage, HttpStatus.BAD_REQUEST);
        }
        responseMessage = new ClkResponseMessage<DetailedFantasyLeague>(detailedFantasyLeague, "EXISTS");
        return new ResponseEntity<ClkResponseMessage>(responseMessage, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*", allowCredentials = "true")
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public ResponseEntity<ClkResponseMessage> updateFantasyLeagueDetails(@RequestBody DetailedFantasyLeague detailedFantasyLeague) {
        ClkResponseMessage<DetailedFantasyLeague> responseMessage;
        int matchId = detailedFantasyLeague.getLeagueMetaDate().getMatchId();
        MatchSchedule matchSchedule = matchScheduleService.getMatchById(matchId);
        if(matchSchedule.getCurrentStatus() != 3) {
            responseMessage = new ClkResponseMessage<DetailedFantasyLeague>(new DetailedFantasyLeague(), "CLOSED");
            return new ResponseEntity<ClkResponseMessage>(responseMessage, HttpStatus.OK);
        }
        DetailedFantasyLeague updatedFantasyLeagueInfo = fantasyLeagueService.updatePlayerFantasyLeagueTeam(detailedFantasyLeague);
        if (updatedFantasyLeagueInfo.getLeagueMetaDate() == null) {
            responseMessage = new ClkResponseMessage<DetailedFantasyLeague>(new DetailedFantasyLeague(), "DOES NOTS EXIST");
            return new ResponseEntity<ClkResponseMessage>(responseMessage, HttpStatus.BAD_REQUEST);
        }
        responseMessage = new ClkResponseMessage<DetailedFantasyLeague>(updatedFantasyLeagueInfo, "EXISTS");
        return new ResponseEntity<ClkResponseMessage>(responseMessage, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*", allowCredentials = "true")
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public ResponseEntity<ClkResponseMessage> addFantasyLeagueDetails(@RequestBody DetailedFantasyLeague detailedFantasyLeague) {
        ClkResponseMessage<Boolean> responseMessage;
        Boolean flag = fantasyLeagueService.addStorePlayerFantasyLeagueTeam(detailedFantasyLeague);
        if (!flag) {
            responseMessage = new ClkResponseMessage<Boolean>(flag, "DOES NOTS EXIST");
            return new ResponseEntity<ClkResponseMessage>(responseMessage, HttpStatus.BAD_REQUEST);
        }
        responseMessage = new ClkResponseMessage<Boolean>(flag, "EXISTS");
        return new ResponseEntity<ClkResponseMessage>(responseMessage, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*", allowCredentials = "true")
    @RequestMapping(method = RequestMethod.GET, value = "/create/{userId}/{matchId}/{structureId}")
    public ResponseEntity<ClkResponseMessage> createFantasyLeagueDetails(@PathVariable("userId") String userId, @PathVariable("matchId") int matchId, @PathVariable("structureId") int structureId) {
        ClkResponseMessage<FantasyLeague> responseMessage;
        Boolean flag = fantasyLeagueService.createFantasyLeague(userId, matchId, structureId);
        if (!flag) {
            responseMessage = new ClkResponseMessage<FantasyLeague>(new FantasyLeague(), "Failed To Create League Entry");
            return new ResponseEntity<ClkResponseMessage>(responseMessage, HttpStatus.BAD_REQUEST);
        }
        FantasyLeague newFantasyLeage = fantasyLeagueService.retrieveFantasyLeague(userId,matchId);
        responseMessage = new ClkResponseMessage<FantasyLeague>(newFantasyLeage, "EXISTS");
        return new ResponseEntity<ClkResponseMessage>(responseMessage, HttpStatus.OK);
    }


    @CrossOrigin(origins = "*", allowCredentials = "true")
    @RequestMapping(method = RequestMethod.GET, value = "/update/scores/{matchId}")
    public ResponseEntity<ClkResponseMessage> updateLeagueScores(@PathVariable("matchId") int matchId) {
        ClkResponseMessage<Boolean> responseMessage;
        Boolean flag = fantasyLeagueService.updateFantasyLeagueScores(matchId);
        if (!flag) {
            responseMessage = new ClkResponseMessage<Boolean>(false, "Failed To Update User Scores");
            return new ResponseEntity<ClkResponseMessage>(responseMessage, HttpStatus.BAD_REQUEST);
        }
        responseMessage = new ClkResponseMessage<Boolean>(true, "Success");
        return new ResponseEntity<ClkResponseMessage>(responseMessage, HttpStatus.OK);
    }

}
