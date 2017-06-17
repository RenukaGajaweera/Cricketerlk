package com.cricketerlk.controllers;

import com.cricketerlk.common.ClkResponseMessage;
import com.cricketerlk.models.user.User;
import com.cricketerlk.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Supun on 5/4/2017.
 */

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @CrossOrigin(origins = "*", allowCredentials = "true")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<ClkResponseMessage> getUserById(@PathVariable("id") String userId) {
        ClkResponseMessage<User> responseMessage;
        User user = userService.getUserById(userId);
        if (user == null) {
            responseMessage = new ClkResponseMessage<User>(user, "INVALID_USER");
        } else {
            responseMessage = new ClkResponseMessage<User>(user, "SUCCESS");
        }
        return new ResponseEntity<ClkResponseMessage>(responseMessage, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*", allowCredentials = "true")
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public ResponseEntity<ClkResponseMessage> addUser(@RequestBody User user) {
        boolean flag = userService.addUser(user);
        if (flag == false) {
            ClkResponseMessage<User> responseMessage = new ClkResponseMessage<User>(new User(), "USER_CREATION_FAILED");
            return new ResponseEntity<ClkResponseMessage>(responseMessage, HttpStatus.CONFLICT);
        }
        User newUser = userService.getUserById(user.getUserId());
        ClkResponseMessage<User> responseMessage = new ClkResponseMessage<User>(newUser, "SUCCESS");
        return new ResponseEntity<ClkResponseMessage>(responseMessage, HttpStatus.OK);
    }

}
