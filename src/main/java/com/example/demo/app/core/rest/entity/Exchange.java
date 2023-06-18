package com.example.demo.app.core.rest.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Exchange")
public class Exchange {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    private String label;

    @NonNull
    private Float price;

    @NonNull
    private Float exchangedPrice;

    private String userKey;
}
