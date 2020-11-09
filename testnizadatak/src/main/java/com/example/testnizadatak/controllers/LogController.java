package com.example.testnizadatak.controllers;

import com.example.testnizadatak.models.Log;
import com.example.testnizadatak.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/log")
public class LogController {

    @Autowired
    LogService logService;

    @GetMapping("/all")
    private List<Log> getAll(){
       return logService.getAlL();
    }
    @PostMapping("/logout")
    private @ResponseBody
    void logout(@RequestBody long userId) {
        logService.logout(userId);
    }
}
