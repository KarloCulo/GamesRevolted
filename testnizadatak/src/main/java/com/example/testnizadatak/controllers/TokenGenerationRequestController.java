package com.example.testnizadatak.controllers;

import com.example.testnizadatak.models.TokenGenerationRequest;
import com.example.testnizadatak.services.TokenGenerationRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/tokengenerationrequest")
public class TokenGenerationRequestController {

    @Autowired
    TokenGenerationRequestService tokenGenerationRequestService;

    @GetMapping("/all/{userId}")
    private List<TokenGenerationRequest> getAll(@PathVariable("userId") long userId){
        return tokenGenerationRequestService.getAll(userId);
    }

    @PostMapping("/generate")
    private @ResponseBody
    boolean activate(@RequestBody long userId) {
        System.out.println("***************************" + userId);
        String response = tokenGenerationRequestService.generateRequest(userId);
        if(!response.equals("OK")){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, response);
        }
        else return true;
    }
}
