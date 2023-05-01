package com.unesp.soo.src.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.unesp.soo.src.entities.TransactionEntity;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    @Modifying
    @Transactional
    @Query(value = "select * from transactions t WHERE t.user_id = :id", nativeQuery = true)
    List<TransactionEntity> getAllTransactionsByUserID(Long id);

}