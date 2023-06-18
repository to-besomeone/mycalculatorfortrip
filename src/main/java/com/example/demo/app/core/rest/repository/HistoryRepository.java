package com.example.demo.app.core.rest.repository;

import com.example.demo.app.core.rest.entity.Exchange;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<Exchange, String> {

    List<Exchange> findExchangeByUserKey(String key);

    void deleteExchangeByUserKeyAndId(String userKey, String id);

}
