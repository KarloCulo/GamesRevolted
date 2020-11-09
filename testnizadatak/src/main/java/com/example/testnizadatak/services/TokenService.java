package com.example.testnizadatak.services;

import com.example.testnizadatak.models.*;
import com.example.testnizadatak.repository.LogRepository;
import com.example.testnizadatak.repository.TokenGenerationRequestRepository;
import com.example.testnizadatak.repository.TokenRepository;
import com.example.testnizadatak.repository.UserAccountRepository;
import com.example.testnizadatak.utility.Crypt;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class TokenService {

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    TokenGenerationRequestRepository tokenGenerationRequestRepository;

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    LogRepository loggerRepository;



    public List<TokenCryptoInfo> getAll(long userId){
        UserAccount userAccount = userAccountRepository.findById(userId);
        List<Token> tokens;

        if(userAccount.getRole().equals("admin")) {
            tokens = tokenRepository.findAll();
        }
        else{
            tokens = tokenRepository.findByOwner(userAccount);
        }
        for(Token t : tokens){
            t.setOwner(unproxyAccount(t.getOwner()));
        }

        return Crypt.encryptedTokens(tokens);
    }

    public void saveOrUpdate(TokenInfo tokenInfo){
        Token token;

        if(tokenInfo.getId() == 0){
        //creating token from approved request
            UserAccount userAccount = userAccountRepository.findById(tokenInfo.getOwnerId());
            tokenGenerationRequestRepository.deleteById(tokenInfo.getRequestId());
            token = new Token();
            token.setBeginDate(new Date());
            token.setActivated(false);
            token.setMark(randomNumber());
            token.setOwner(userAccount);
        }
        else{
            token = tokenRepository.findById(tokenInfo.getId());
        }

        token.setEndDate(tokenInfo.getEndDate());
        token.setValue(tokenInfo.getValue());
        tokenRepository.save(token);
    }

    public String activateToken(ActivationInfo activationInfo){
        String result = "OK";
        Token token = null;
        UserAccount userAccount = userAccountRepository.findById(activationInfo.getUserId());
        Log saveLog = new Log();
        saveLog.setTime(new Date());

        if (!activationInfo.getInputMark().matches("[0-9]+")){
            result = " Wrong input: must be numbers only!";
        }
        else{
            token = tokenRepository.findByMark(activationInfo.getInputMark());
            if(token == null){
                result = " Wrong input: requested token not found.";
            }
            else{
                if(token.getOwner().getId() != userAccount.getId()){
                    result = " Unauthorized: you are not token owner.";
                }
                else if(token.isActivated()){
                    result = " Token is already activated.";
                }
                else if(token.getEndDate().before(new Date())){
                    result = " Token has expired.";
                }
            }
        }

        saveLog.setUserName(userAccount.getName());
        saveLog.setDescription("Token activation failed due" + result);

        if(result.equals("OK")){
            token.setActivated(true);
            float newUserBalance = userAccount.getBalance() + token.getValue();
            userAccount.setBalance(newUserBalance);
            tokenRepository.save(token);
            userAccountRepository.save(userAccount);
            saveLog.setDescription("Token activation was successful. Balance is now: " + newUserBalance);
        }
        loggerRepository.save(saveLog);

        return result;
    }

    private UserAccount unproxyAccount(UserAccount user){
        UserAccount unproxiedEntity = (UserAccount) Hibernate.unproxy(user);
        return unproxiedEntity;
    }

    private String randomNumber(){
        Random random = new Random();
        StringBuffer number = new StringBuffer();
        String mark;

        //stvaraj oznaku bona, sve dok ne napraviš jedinstveni
        do{
            number.setLength(0);
            for(int i = 0; i < 10; i++){
                number.append(random.nextInt(10));
            }
        }while (tokenRepository.findByMark(number.toString()) != null);

        return number.toString();
    }

}
