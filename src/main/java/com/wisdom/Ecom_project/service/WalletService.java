package com.wisdom.Ecom_project.service;

import com.wisdom.Ecom_project.exception.InsufficientWalletBalanceException;
import com.wisdom.Ecom_project.model.TransactionType;
import com.wisdom.Ecom_project.model.Wallet;
import com.wisdom.Ecom_project.model.WalletTransaction;
import com.wisdom.Ecom_project.repository.WalletRepo;
import com.wisdom.Ecom_project.repository.WalletTransactionRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WalletService {

   @Autowired
    private WalletRepo walletRepo;

   @Autowired
    private WalletTransactionRepo walletTransactionRepo;

    @Transactional
    public void debitwallet(String userEmail, BigDecimal amount, String usageDescription){
        Wallet wallet = walletRepo.findByUserEmailForUpdate(userEmail)
                .orElseThrow(() -> new RuntimeException(""));

        if(wallet.getBalance().compareTo(amount) < 0){
            throw new InsufficientWalletBalanceException("");
        }

        wallet.setBalance(wallet.getBalance().subtract(amount));
        walletRepo.save(wallet);

        WalletTransaction transaction = new WalletTransaction();
        transaction.setWallet(wallet);
        transaction.setAmount(amount);
        transaction.setType(TransactionType.DEBIT);
        transaction.setDescription(usageDescription);
        walletTransactionRepo.save(transaction);
    }


}
