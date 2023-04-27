package com.unesp.soo.src.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InsertingClientDTO {
    String name;
    String cpf;
    Double initialAccountValue;
}