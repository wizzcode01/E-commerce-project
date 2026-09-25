package com.wisdom.Ecom_project.repository;

import com.wisdom.Ecom_project.model.WalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletTransactionRepo extends JpaRepository<WalletTransaction, Long> {

}
