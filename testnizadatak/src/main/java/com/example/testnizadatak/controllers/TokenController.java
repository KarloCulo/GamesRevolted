package com.example.testnizadatak.controllers;

import com.example.testnizadatak.models.ActivationInfo;
import com.example.testnizadatak.models.TokenCryptoInfo;
import com.example.testnizadatak.models.TokenInfo;
import com.example.testnizadatak.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/token")
public class TokenController {

    @Autowired
    TokenService tokenService;

    @GetMapping("/all/{userId}")
    private List<TokenCryptoInfo> getAll(@PathVariable("userId") long userId){
        return tokenService.getAll(userId);
    }

    @PostMapping("/save")
    private @ResponseBody void save(@RequestBody TokenInfo tokenInfo){
        tokenService.saveOrUpdate(tokenInfo);
    }

    @PostMapping("/activate")
    private @ResponseBody
    boolean activate(@RequestBody ActivationInfo activationInfo) {
        String response = tokenService.activateToken(activationInfo);
        if(!response.equals("OK")){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, response);
        }
        else return true;
    }
}
