package com.unesp.soo.src.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class APIResponseDTO<T> {
    Integer code;
    T data;
}
