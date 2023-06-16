package com.mm.com.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class MessageDto {
    private HttpStatus status;
    private String message;
}
