package com.example.demo.app.core.rest.service;

import com.example.demo.app.core.rest.repository.HistoryRepository;
import com.example.demo.app.core.rest.entity.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryRepository historyRepository;



    public List<Exchange> getHistoryList(String userKey){
        List<Exchange> result = historyRepository.findExchangeByUserKey(userKey);
        return result;
    }

    public Exchange create(Exchange ex){
        ex.setId("test");
        validate(ex);
        ex.setId(null);
        try{
            Exchange saved = historyRepository.save(ex);
            return saved;
        }
        catch (Exception e){
//            throw e;
            throw new RuntimeException("Error occurred during saving search history");
        }
    }

    public void delete(String userKey, String id){
        validate(userKey, id);
        try{

            historyRepository.deleteExchangeByUserKeyAndId(userKey, id);
        }
        catch (Exception e){
//            throw e;
            throw new RuntimeException("Error occurred during deleting search history: " + id);
        }
    }

    private void validate(Exchange ex) {
        if (ex == null)
        {
            throw new RuntimeException("Entity Cannot be null");
        }
        else if (ex.getPrice() == null || ex.getExchangedPrice() == null) {
            throw new RuntimeException("Price cannot be empty");
        }
        else{
            validate(ex.getUserKey(), ex.getId());
        }
    }

    private void validate(String userKey, String id){
        if (id == null || "".equals(id))
            throw new RuntimeException("Id cannot be empty");
        if (userKey == null || "".equals(userKey))
            throw new RuntimeException("Unknown User");
    }
}
