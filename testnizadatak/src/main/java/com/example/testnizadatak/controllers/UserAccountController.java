package com.example.testnizadatak.controllers;

import com.example.testnizadatak.models.LoginInfo;
import com.example.testnizadatak.models.UserAccount;
import com.example.testnizadatak.repository.UserAccountRepository;
import com.example.testnizadatak.services.UserAccountService;
import org.h2.engine.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/useraccount")
public class UserAccountController {

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    UserAccountRepository userAccountRepository;

    @GetMapping("/allUsers")
    private List<UserAccount> findAllUsers() {
        return userAccountRepository.findAll();
    }

    @GetMapping("/all/{userId}")
    private List<UserAccount> getAll(@PathVariable("userId") long userId){
        return userAccountService.getUserAccounts(userId);
    }

    @PostMapping("/save")
    private @ResponseBody void save(@RequestBody UserAccount userAccount){
        userAccountService.saveOrUpdate(userAccount);
    }

    @PostMapping("/login")
    private @ResponseBody
    UserAccount loginUser(@RequestBody LoginInfo user) {
        UserAccount userAccount = userAccountService.checkLogin(user);

        if(userAccount != null)
            return  userAccount;
        else
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect username or password ");

    }

}
