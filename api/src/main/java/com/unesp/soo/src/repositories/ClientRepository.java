package com.unesp.soo.src.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.unesp.soo.src.entities.ClientEntity;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE ClientEntity c SET c.accountValue = c.accountValue + :value WHERE c.id = :id")
    void sumIntoAccountValueById(Long id, Double value);

    @Modifying
    @Transactional
    @Query("UPDATE ClientEntity c SET c.accountValue = c.accountValue - :value WHERE c.id = :id")
    void subtractFromAccountValueById(Long id, Double value);

}