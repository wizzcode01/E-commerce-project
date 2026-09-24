package com.wisdom.Ecom_project.exception;

public class InsufficientWalletBalanceException extends RuntimeException{
    public InsufficientWalletBalanceException(String message){
        super(message);
    }
}
