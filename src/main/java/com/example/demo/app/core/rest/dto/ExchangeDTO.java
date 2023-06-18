package com.example.demo.app.core.rest.dto;


import com.example.demo.app.core.rest.entity.Exchange;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeDTO {
    @Nullable
    private String id;

    private String label;

    private String price;

    private String exchangedPrice;

    private String userKey;

    public ExchangeDTO(final Exchange exchange)
    {
        this.id = exchange.getId();
        this.label = exchange.getLabel();
        this.price = exchange.getPrice().toString();
        this.exchangedPrice = exchange.getExchangedPrice().toString();
        this.userKey = exchange.getUserKey();
    }

    public Exchange toEntity(final ExchangeDTO exchangeDTO){
        return Exchange.builder()
                .id(exchangeDTO.id)
                .label(exchangeDTO.label)
                .price(Float.valueOf(exchangeDTO.price))
                .exchangedPrice(Float.valueOf(exchangeDTO.exchangedPrice))
                .userKey(exchangeDTO.userKey)
                .build();
    }

}
