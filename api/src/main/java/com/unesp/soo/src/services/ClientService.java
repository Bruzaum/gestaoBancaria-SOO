package com.unesp.soo.src.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unesp.soo.src.dtos.ClientDTO;
import com.unesp.soo.src.dtos.InsertingClientDTO;
import com.unesp.soo.src.entities.ClientEntity;
import com.unesp.soo.src.repositories.ClientRepository;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;

    private ClientDTO fromEntityToDTO(ClientEntity clientEntity) {

        return ClientDTO.builder()
                .name(clientEntity.getName())
                .cpf(clientEntity.getCpf())
                .accountValue(clientEntity.getAccountValue())
                .build();
    }

    private ClientEntity fromInsertingDTOToEntity(InsertingClientDTO clientDTO) {

        return ClientEntity.builder()
                .name(clientDTO.getName())
                .cpf(clientDTO.getCpf())
                .accountValue(clientDTO.getInitialAccountValue())
                .build();
    }

    public void insertClient(InsertingClientDTO insertingClientDTO) {
        ClientEntity clientEntity = fromInsertingDTOToEntity(insertingClientDTO);
        repository.save(clientEntity);
    }

    public ClientDTO findById(Long id) {
        ClientEntity clientEntity = repository.findById(id).orElse(null);
        return fromEntityToDTO(clientEntity);
    }

    public ClientDTO sumIntoAccountValueById(Long id, double value) {
        ClientEntity clientEntity = repository.sumIntoAccountValueById(id, value);
        return fromEntityToDTO(clientEntity);
    }

    public ClientDTO subtractFromAccountValueById(Long id, double value) {
        ClientEntity clientEntity = repository.subtractFromAccountValueById(id, value);
        return fromEntityToDTO(clientEntity);
    }
}
