package com.unesp.soo.src.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unesp.soo.src.dtos.TransactionDTO;
import com.unesp.soo.src.entities.TransactionEntity;
import com.unesp.soo.src.repositories.TransactionRepository;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository repository;

    private TransactionDTO fromEntityToDTO(TransactionEntity trasactionEntity) {

        return TransactionDTO.builder()
                .user_id(trasactionEntity.getUser_id())
                .type(trasactionEntity.getType())
                .value(trasactionEntity.getValue())
                .build(); 
    }

    private TransactionEntity fromInsertingDTOToEntity(TransactionDTO transactionDTO) {

        return TransactionEntity.builder()
                .user_id(transactionDTO.getUser_id())
                .type(transactionDTO.getType())
                .value(transactionDTO.getValue())
                .build(); 
    }

    public void insertTransaction(TransactionDTO transactionDTO) {
        TransactionEntity transactionEntity = fromInsertingDTOToEntity(transactionDTO);
        repository.save(transactionEntity);

        // return fromEntityToDTO(transactionEntity);
    }

    public List<TransactionDTO> getClientHistory(Long id) {
        List<TransactionEntity>  transactionEntities = repository.getAllTransactionsByUserID(id);
        List<TransactionDTO>  transactionDTOs= new ArrayList<TransactionDTO>();

        for(TransactionEntity transaction: transactionEntities){
            transactionDTOs.add(fromEntityToDTO(transaction));
        }
        return transactionDTOs;
    }
}
