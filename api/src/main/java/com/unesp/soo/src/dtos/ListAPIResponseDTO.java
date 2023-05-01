package com.unesp.soo.src.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ListAPIResponseDTO<T> {
    Integer code;
    List<T> data;
}