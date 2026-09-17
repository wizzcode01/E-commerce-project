package com.wisdom.Ecom_project.dto.ResponseDto;

import java.time.LocalDateTime;

public class responseDto<T> {
    private LocalDateTime timestamp;
    private String message;
    private T data;

    public responseDto(String message){
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public responseDto(String message, T data){
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.data = data;
    }

    public LocalDateTime getTimestamp(){
        return timestamp;
    }
    public String getMessage(){
        return message;
    }

    public T getData(){
        return data;
    }

}
