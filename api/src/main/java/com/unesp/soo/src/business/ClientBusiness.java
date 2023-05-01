package com.unesp.soo.src.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unesp.soo.src.dtos.ClientDTO;
import com.unesp.soo.src.dtos.DepositDTO;
import com.unesp.soo.src.dtos.InsertingClientDTO;
import com.unesp.soo.src.dtos.TransferDTO;
import com.unesp.soo.src.dtos.WithdrawDTO;
import com.unesp.soo.src.dtos.TransactionDTO;
import com.unesp.soo.src.enums.RESTStatusEnum;
import com.unesp.soo.src.exceptions.APIException;
import com.unesp.soo.src.services.ClientService;
import com.unesp.soo.src.services.TransactionService;

import jakarta.transaction.Transactional;

@Service
public class ClientBusiness {

    @Autowired
    private ClientService service;
    @Autowired
    private TransactionService tService;

    public ClientDTO newClient(InsertingClientDTO insertingClientDTO) {
        return service.insertClient(insertingClientDTO);
    }

    public ClientDTO getClient(Long id) {
        return service.findById(id);
    }

    public ClientDTO deposit(DepositDTO deposit) {
        service.sumIntoAccountValueById(deposit.getClientId(), deposit.getValue());
        TransactionDTO transaction = TransactionDTO.builder()
                                            .user_id(deposit.getClientId())
                                            .type("Deposit")
                                            .value(deposit.getValue())
                                            .build(); 
        tService.insertTransaction(transaction);
        return getClient(deposit.getClientId());
    }

    public ClientDTO withdraw(WithdrawDTO withdraw) {
        ClientDTO client = service.findById(withdraw.getClientId());

        if (client.getAccountValue() >= withdraw.getValue()) {
            service.subtractFromAccountValueById(
                withdraw.getClientId(),
                withdraw.getValue()
            );

            TransactionDTO transaction = TransactionDTO.builder()
                                            .user_id(withdraw.getClientId())
                                            .type("Withdraw")
                                            .value(withdraw.getValue())
                                            .build(); 
            tService.insertTransaction(transaction);

            return getClient(withdraw.getClientId());
        }

        return null;
    }

    @Transactional
    public String transferBetweenClients(TransferDTO transferDTO) throws APIException {
        if (transferDTO.getSenderId() == transferDTO.getReceiverId()) {
            throw new APIException("You cant make a transference for yourself.", RESTStatusEnum.BAD_REQUEST.code);
        }
        
        TransactionDTO transaction = TransactionDTO.builder()
                                            .user_id(transferDTO.getSenderId())
                                            .type("Transfer")
                                            .value(transferDTO.getValue())
                                            .build(); 
        tService.insertTransaction(transaction);

        ClientDTO sender = service.findById(transferDTO.getSenderId());
        
        if (sender.getAccountValue() < transferDTO.getValue()) {
            throw new APIException(
                "The amount available in the account was less than the amount to be transferred. Please check account balance.", 
                RESTStatusEnum.BAD_REQUEST.code
            );
        }

        service.subtractFromAccountValueById(transferDTO.getSenderId(), transferDTO.getValue());
        service.sumIntoAccountValueById(transferDTO.getReceiverId(), transferDTO.getValue());

        return "Transaction made successfully!";
    }
}
