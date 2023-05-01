package com.unesp.soo.src.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unesp.soo.src.dtos.TransactionDTO;
import com.unesp.soo.src.services.TransactionService;


@Service
public class TransactionBusiness {

    @Autowired
    private TransactionService service;

    public void newTransaction(TransactionDTO transactionDTO) {
        service.insertTransaction(transactionDTO);
    }

    public List<TransactionDTO> getClientHistory(Long id) {
        return service.getClientHistory(id);
    }
}
