package com.unesp.soo.src.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unesp.soo.src.dtos.ClientDTO;
import com.unesp.soo.src.dtos.InsertingClientDTO;
import com.unesp.soo.src.services.ClientService;

import jakarta.transaction.Transactional;

@Service
public class ClientBusiness {

    @Autowired
    private ClientService service;

    public void newClient(InsertingClientDTO insertingClientDTO) {
        service.insertClient(insertingClientDTO);
    }

    public ClientDTO getClient(Long id) {
        return service.findById(id);
    }

    public ClientDTO deposit(Long id, double value) {
        return service.sumIntoAccountValueById(id, value);
    }

    public ClientDTO withdraw(Long id, double value) {
        ClientDTO client = service.findById(id);

        if (client.getAccountValue() >= value) {
            return service.subtractFromAccountValueById(id, value);
        }

        return null;
    }

    @Transactional
    public void transferBetweenClients(Long senderId, Long receiverId, double value) {
        ClientDTO sender = service.findById(senderId);

        if (sender.getAccountValue() >= value) {
            service.subtractFromAccountValueById(senderId, value);
            service.sumIntoAccountValueById(receiverId, value);
        }

        return;
    }
}
