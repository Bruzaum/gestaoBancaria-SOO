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

    public ClientDTO insertClient(InsertingClientDTO insertingClientDTO) {
        ClientEntity clientEntity = fromInsertingDTOToEntity(insertingClientDTO);
        repository.save(clientEntity);

        return fromEntityToDTO(clientEntity);
    }

    public ClientDTO findById(Long id) {
        ClientEntity clientEntity = repository.findById(id).orElse(null);
        return fromEntityToDTO(clientEntity);
    }

    public void sumIntoAccountValueById(Long id, double value) {
        repository.sumIntoAccountValueById(id, value);
    }

    public void subtractFromAccountValueById(Long id, double value) {
        repository.subtractFromAccountValueById(id, value);
    }
}
