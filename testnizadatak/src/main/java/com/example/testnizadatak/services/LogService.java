package com.example.testnizadatak.services;

import com.example.testnizadatak.models.Log;
import com.example.testnizadatak.models.UserAccount;
import com.example.testnizadatak.repository.LogRepository;
import com.example.testnizadatak.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LogService {

    @Autowired
    LogRepository loggerRepository;

    @Autowired
    UserAccountRepository userAccountRepository;

    public List<Log> getAlL(){
        return loggerRepository.findAll();
    }

    public void logout(long userId) {
        UserAccount userAccount = userAccountRepository.findById(userId);
        if(userAccount.getRole().equals("user")){
            Log saveLog = new Log();
            saveLog.setTime(new Date());
            saveLog.setUserName(userAccount.getName());
            saveLog.setDescription("Logout.");
            loggerRepository.save(saveLog);
        }
    }
}
