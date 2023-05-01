package com.unesp.soo.src.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionDTO {
    Long user_id;
    double value;
    String type;
}
