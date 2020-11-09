package com.example.testnizadatak.services;

import com.example.testnizadatak.models.Log;
import com.example.testnizadatak.models.LoginInfo;
import com.example.testnizadatak.models.UserAccount;
import com.example.testnizadatak.repository.LogRepository;
import com.example.testnizadatak.repository.TokenGenerationRequestRepository;
import com.example.testnizadatak.repository.TokenRepository;
import com.example.testnizadatak.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserAccountService {

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    LogRepository loggerRepository;

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    TokenGenerationRequestRepository tokenGenerationRequestRepository;


    public List<UserAccount> getUserAccounts(long userId) {
        UserAccount userAccount = userAccountRepository.findById(userId);
        List<UserAccount> returnList = new ArrayList<>();

        if(userAccount.getRole().equals("user")){
            returnList.add(userAccount);
        }
        else{
            returnList =  userAccountRepository.findAll();
        }
        return returnList;
    }

    public void saveOrUpdate(UserAccount userAccount){
        if(!userAccount.getRole().equals("user") && !userAccount.getRole().equals("admin")){
            userAccount.setRole("user");
        }
        userAccountRepository.save(userAccount);
    }

    public UserAccount checkLogin(LoginInfo user) {
        for (UserAccount userAccount : userAccountRepository.findAll()) {
            if (userAccount.getName().equals(user.getUsername()) && userAccount.getPassword().equals(user.getPassword())){
                isRoleUser(userAccount);
                return userAccount;
            }
        }
        return null;
    }

    private void isRoleUser(UserAccount userAccount) {
        if(userAccount.getRole().equals("user")){
            Log saveLog = new Log();
            saveLog.setTime(new Date());
            saveLog.setUserName(userAccount.getName());
            saveLog.setDescription("Login.");
            loggerRepository.save(saveLog);
        }
    }
}
