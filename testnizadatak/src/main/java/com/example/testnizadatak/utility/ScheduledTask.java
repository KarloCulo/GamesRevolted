package com.example.testnizadatak.utility;

import com.example.testnizadatak.models.Token;
import com.example.testnizadatak.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class ScheduledTask {

    @Autowired
    TokenRepository tokenRepository;

    //delete unnecessary tokens every 45 seconds
    @Scheduled(cron="*/45 * * * * ?")
    public void checkTokens() {
        System.out.println("Checked Tokens");
        List<Token> all = tokenRepository.findAll();
        for(Token token : all){
            if(token.getEndDate().before(new Date()) || token.isActivated()){
                tokenRepository.delete(token);
            }
        }
    }
}
