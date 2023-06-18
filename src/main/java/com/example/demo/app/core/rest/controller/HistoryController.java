package com.example.demo.app.core.rest.controller;


import com.example.demo.app.core.rest.dto.ResponseDTO;
import com.example.demo.app.core.rest.entity.Exchange;
import com.example.demo.app.core.rest.dto.ExchangeDTO;
import com.example.demo.app.core.rest.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @DeleteMapping("/{userKey}/{id}")
    public ResponseEntity deleteHistory(@PathVariable String userKey, @PathVariable String id){
        try{
            historyService.delete(userKey, id);
            return ResponseEntity.noContent().build();
        }
        catch(Exception e){
            ResponseDTO<ExchangeDTO> dto = ResponseDTO.<ExchangeDTO>builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(dto);
        }
    }

    @GetMapping("/{userKey}")
    public ResponseEntity getHistoryList(@PathVariable String userKey){

        List<Exchange> entities = historyService.getHistoryList(userKey);
        List<ExchangeDTO> result = entities.stream().map(ExchangeDTO::new).collect(Collectors.toList());
        ResponseDTO<ExchangeDTO> response = ResponseDTO.<ExchangeDTO>builder().data(result).build();
        return ResponseEntity.ok(response);

    }
    @PostMapping("/{userKey}")
    public ResponseEntity postHistory(@PathVariable String userKey, @RequestBody ExchangeDTO exchangeDTO){

        try{
            exchangeDTO.setUserKey(userKey);
            Exchange entity = exchangeDTO.toEntity(exchangeDTO);
            Exchange savedHistory = historyService.create(entity);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedHistory.getId())
                    .toUri();

            return ResponseEntity.created(location).body(savedHistory.getId());
        }
        catch(Exception e)
        {
            String message = e.getMessage();
            ResponseDTO<ExchangeDTO> dto = ResponseDTO.<ExchangeDTO>builder().error(message).build();
            return ResponseEntity.badRequest().body(dto);
        }

    }
}
