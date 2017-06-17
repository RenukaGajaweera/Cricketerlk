package com.cricketerlk.controllers;

import com.cricketerlk.common.ClkResponseMessage;
import com.cricketerlk.models.player.Player;
import com.cricketerlk.services.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Supun on 5/5/2017.
 */

@Controller
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private IPlayerService playerService;

    @CrossOrigin(origins = "*", allowCredentials = "true")
    @GetMapping("/all")
    public ResponseEntity<List<Player>> getAllPlayers() {
        List<Player> players = playerService.getAllPlayers();
        return new ResponseEntity<List<Player>>(players, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*", allowCredentials = "true")
    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable("id") Integer id) {
        Player player = playerService.getPlayerById(id);
        return new ResponseEntity<Player>(player, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*", allowCredentials = "true")
    @GetMapping("/country/{countryCode}")
    public ResponseEntity<ClkResponseMessage> getPlayersByCountryCode(@PathVariable("countryCode") int countryCode) {
        ClkResponseMessage<List<Player>> reponseMessage;
        List<Player> players = playerService.getAllPlayersByCountryCode(countryCode);
        if(!players.isEmpty()) {
            reponseMessage = new ClkResponseMessage<List<Player>>(players, "SUCCESS");
            return new ResponseEntity<ClkResponseMessage>(reponseMessage, HttpStatus.OK);
        }
        reponseMessage = new ClkResponseMessage<List<Player>>(players, "NO CONTENT");
        return new ResponseEntity<ClkResponseMessage> (reponseMessage, HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin(origins = "*", allowCredentials = "true")
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public ResponseEntity<ClkResponseMessage> addPlayer(@RequestBody Player player) {
        boolean flag = playerService.addPlayer(player);
        if (flag == false) {
            ClkResponseMessage<Player> responseMessage = new ClkResponseMessage<Player>(new Player(), "USER_CREATION_FAILED");
            return new ResponseEntity<ClkResponseMessage>(responseMessage, HttpStatus.BAD_REQUEST);
        }
        List<Player> players = playerService.getAllPlayersByName(player.getPlayerName());
        ClkResponseMessage<List<Player>> responseMessage = new ClkResponseMessage<List<Player>>(players, "SUCCESS");
        return new ResponseEntity<ClkResponseMessage>(responseMessage, HttpStatus.OK);
    }

}
