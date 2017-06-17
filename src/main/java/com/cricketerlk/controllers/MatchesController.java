package com.cricketerlk.controllers;

import com.cricketerlk.common.ClkResponseMessage;
import com.cricketerlk.models.matchschedule.MatchSchedule;
import com.cricketerlk.services.IMatchScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

/**
 * Created by Supun on 6/3/2017.
 */

@Controller
@RequestMapping("/schedule")
public class MatchesController {

    @Autowired
    private IMatchScheduleService matchScheduleService;

    @CrossOrigin(origins = "*", allowCredentials = "true")
    @GetMapping("/all")
    public ResponseEntity<ClkResponseMessage> getAllMatchData() {
        ClkResponseMessage<List<MatchSchedule>> clkResponse;
        List<MatchSchedule> schedules = matchScheduleService.getAllMatchSchedules();
        if(schedules.isEmpty()) {
            clkResponse= new ClkResponseMessage<List<MatchSchedule>>(schedules, "No Content");
            return new ResponseEntity<ClkResponseMessage>(clkResponse, HttpStatus.NOT_FOUND);
        }
        clkResponse = new ClkResponseMessage<>(schedules, "SUCCESS");
        return new ResponseEntity<ClkResponseMessage>(clkResponse, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*", allowCredentials = "true")
    @RequestMapping(method = RequestMethod.GET, value = "/{matchId}")
    public ResponseEntity<ClkResponseMessage> getMatchById(@PathVariable("matchId") int matchId) {
        ClkResponseMessage<MatchSchedule> clkResponse;
        MatchSchedule schedule = matchScheduleService.getMatchById(matchId);
        if(schedule == null) {
            clkResponse= new ClkResponseMessage<MatchSchedule>(schedule, "No Content");
            return new ResponseEntity<ClkResponseMessage>(clkResponse, HttpStatus.NOT_FOUND);
        }
        clkResponse = new ClkResponseMessage<MatchSchedule>(schedule, "SUCCESS");
        return new ResponseEntity<ClkResponseMessage>(clkResponse, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*", allowCredentials = "true")
    @RequestMapping(method = RequestMethod.GET, value = "/date/{date}")
    public ResponseEntity<ClkResponseMessage> getMatchByDate(@PathVariable("date") Date date) {
        ClkResponseMessage<List<MatchSchedule>> clkResponse;
        List<MatchSchedule> schedules = matchScheduleService.getMatchByDate(date);
        if(schedules.isEmpty()) {
            clkResponse= new ClkResponseMessage<List<MatchSchedule>>(schedules, "No Content");
            return new ResponseEntity<ClkResponseMessage>(clkResponse, HttpStatus.NOT_FOUND);
        }
        clkResponse = new ClkResponseMessage<>(schedules, "SUCCESS");
        return new ResponseEntity<ClkResponseMessage>(clkResponse, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*", allowCredentials = "true")
    @RequestMapping(method = RequestMethod.GET, value = "/status/{status}")
    public ResponseEntity<ClkResponseMessage> getMatchByStatus(@PathVariable("status") int status) {
        ClkResponseMessage<List<MatchSchedule>> clkResponse;
        List<MatchSchedule> schedules = matchScheduleService.getMatchByStatus(status);
        if(schedules.isEmpty()) {
            clkResponse= new ClkResponseMessage<List<MatchSchedule>>(schedules, "No Content");
            return new ResponseEntity<ClkResponseMessage>(clkResponse, HttpStatus.NOT_FOUND);
        }
        clkResponse = new ClkResponseMessage<>(schedules, "SUCCESS");
        return new ResponseEntity<ClkResponseMessage>(clkResponse, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*", allowCredentials = "true")
    @RequestMapping(method = RequestMethod.GET, value = "/current")
    public ResponseEntity<ClkResponseMessage> getOnGoing(@RequestParam("fromDate") Date fromDate, @RequestParam("toDate") Date toDate) {
        ClkResponseMessage<List<MatchSchedule>> clkResponse;
        List<MatchSchedule> schedules = matchScheduleService.getOnGoingMatchDetails(fromDate, toDate);
        if(schedules.isEmpty()) {
            clkResponse= new ClkResponseMessage<List<MatchSchedule>>(schedules, "No Content");
            return new ResponseEntity<ClkResponseMessage>(clkResponse, HttpStatus.NOT_FOUND);
        }
        clkResponse = new ClkResponseMessage<>(schedules, "SUCCESS");
        return new ResponseEntity<ClkResponseMessage>(clkResponse, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*", allowCredentials = "true")
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public ResponseEntity<ClkResponseMessage> addMatch(@RequestBody MatchSchedule matchSchedule) {
        boolean flag = matchScheduleService.addMatch(matchSchedule);
        if (flag == false) {
            ClkResponseMessage<MatchSchedule> responseMessage = new ClkResponseMessage<MatchSchedule>(new MatchSchedule(), "SCHEDULE_CREATION_FAILED");
            return new ResponseEntity<ClkResponseMessage>(responseMessage, HttpStatus.BAD_REQUEST);
        }
        List<MatchSchedule> matchScheduleList = matchScheduleService.getMatchByDate(matchSchedule.getMatchDate());
        ClkResponseMessage<List<MatchSchedule>> responseMessage = new ClkResponseMessage<List<MatchSchedule>>(matchScheduleList, "SUCCESS");
        return new ResponseEntity<ClkResponseMessage>(responseMessage, HttpStatus.OK);
    }

}
