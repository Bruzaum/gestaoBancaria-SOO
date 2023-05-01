package com.unesp.soo.src.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transactions", schema = "bank")
public class TransactionEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long user_id;
    
    @Column(nullable = false)
    private String type;
    
    @Column(nullable = false)
    private Double value = 0.0;    
}