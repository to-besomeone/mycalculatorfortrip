package com.example.demo.app.core.rest.service;

import com.example.demo.app.core.rest.dto.ExchangeDTO;
import com.example.demo.app.core.rest.entity.Exchange;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface HistoryService {
    List<Exchange> getHistoryList(String userKey);

    Exchange create(Exchange ex);

    void delete(String userKey, String id);
}
