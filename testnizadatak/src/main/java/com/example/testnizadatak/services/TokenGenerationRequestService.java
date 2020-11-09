package com.example.testnizadatak.services;

import com.example.testnizadatak.models.Log;
import com.example.testnizadatak.models.TokenGenerationRequest;
import com.example.testnizadatak.models.UserAccount;
import com.example.testnizadatak.repository.LogRepository;
import com.example.testnizadatak.repository.TokenGenerationRequestRepository;
import com.example.testnizadatak.repository.TokenRepository;
import com.example.testnizadatak.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class TokenGenerationRequestService {

    @Autowired
    TokenGenerationRequestRepository tokenGenerationRequestRepository;

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    LogRepository loggerRepository;

    @Autowired
    UserAccountRepository userAccountRepository;

    public List<TokenGenerationRequest> getAll(long userId){
        UserAccount userAccount = userAccountRepository.findById(userId);
        List<TokenGenerationRequest> returnList;

        if(userAccount.getRole().equals("admin")) {
            returnList = tokenGenerationRequestRepository.findAll();
        }
        else{
            returnList = tokenGenerationRequestRepository.findAllByUserId(userId);
        }
        return returnList;
    }

    public String generateRequest(long userId) {
        String result = "OK";
        UserAccount userAccount = userAccountRepository.findById(userId);
        Log saveLog = new Log();
        saveLog.setTime(new Date());
        saveLog.setUserName(userAccount.getName());

        if(userAccount.getRequestsAvailable() <= 0){
            result = " Not enough generation requests available.";
            saveLog.setDescription("Token generation request failed due" + result);
        }
        else{
            TokenGenerationRequest request = new TokenGenerationRequest(userAccount.getId(), userAccount.getName());
            int requestsLeft = userAccount.getRequestsAvailable() - 1;
            userAccount.setRequestsAvailable(requestsLeft);
            userAccountRepository.save(userAccount);
            tokenGenerationRequestRepository.save(request);
            saveLog.setDescription("Token generation request successfully added.");
        }

        loggerRepository.save(saveLog);
        return result;
    }
}
