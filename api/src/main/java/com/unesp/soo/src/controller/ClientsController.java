package com.unesp.soo.src.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unesp.soo.src.business.ClientBusiness;
import com.unesp.soo.src.business.TransactionBusiness;
import com.unesp.soo.src.dtos.APIResponseDTO;
import com.unesp.soo.src.dtos.ListAPIResponseDTO;
import com.unesp.soo.src.dtos.ClientDTO;
import com.unesp.soo.src.dtos.DepositDTO;
import com.unesp.soo.src.dtos.InsertingClientDTO;
import com.unesp.soo.src.dtos.TransferDTO;
import com.unesp.soo.src.dtos.WithdrawDTO;
import com.unesp.soo.src.dtos.TransactionDTO;
import com.unesp.soo.src.enums.RESTStatusEnum;
import com.unesp.soo.src.exceptions.APIException;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/clients")
public class ClientsController {

    @Autowired
    private ClientBusiness business;
    @Autowired
    private TransactionBusiness tBusiness;

    @RequestMapping("/{id}")
    public APIResponseDTO<ClientDTO> getClient(@PathVariable("id") Long id, HttpServletResponse response) {
        try{
            ClientDTO client = business.getClient(id);

            response.setStatus(RESTStatusEnum.OK.code); 
            return new APIResponseDTO<ClientDTO>(
                RESTStatusEnum.OK.code,
                client
            );
        
        } catch(Exception e){

            response.setStatus(RESTStatusEnum.INTERNAL_SERVER_ERROR.code); 
            return new APIResponseDTO<ClientDTO>(
                RESTStatusEnum.INTERNAL_SERVER_ERROR.code,
                null
            );
        }
    }

    @RequestMapping("/history/{id}")
    public ListAPIResponseDTO<TransactionDTO> clientHistory(@PathVariable("id") Long id, HttpServletResponse response) {
        try{
            List<TransactionDTO> client = tBusiness.getClientHistory(id);

            response.setStatus(RESTStatusEnum.OK.code); 
            return new ListAPIResponseDTO<TransactionDTO>(
                RESTStatusEnum.OK.code,
                client
            ); 
        
        } catch(Exception e){

            response.setStatus(RESTStatusEnum.INTERNAL_SERVER_ERROR.code); 
            return new ListAPIResponseDTO<TransactionDTO>(
                RESTStatusEnum.INTERNAL_SERVER_ERROR.code,
                null
            );
        }
    }
    
    @PostMapping("/newClient")
    public APIResponseDTO<ClientDTO> newClient(@RequestBody InsertingClientDTO insertingClientDTO, HttpServletResponse response) {
        try{
            ClientDTO client = business.newClient(insertingClientDTO);

            response.setStatus(RESTStatusEnum.OK.code); 
            return new APIResponseDTO<ClientDTO>(
                RESTStatusEnum.OK.code,
                client
            );

        } catch(Exception e){
            response.setStatus(RESTStatusEnum.INTERNAL_SERVER_ERROR.code); 
            return new APIResponseDTO<ClientDTO>(
                RESTStatusEnum.INTERNAL_SERVER_ERROR.code,
                null
            );
        }
    }
    
    @PostMapping("/deposit")
    public APIResponseDTO<ClientDTO> deposit(@RequestBody DepositDTO depositDTO, HttpServletResponse response) {
        try{
            ClientDTO client = business.deposit(depositDTO);
            
            response.setStatus(RESTStatusEnum.OK.code); 
            return new APIResponseDTO<ClientDTO>(
                RESTStatusEnum.OK.code,
                client
            );
                
        } catch(Exception e){
            response.setStatus(RESTStatusEnum.INTERNAL_SERVER_ERROR.code); 
            return new APIResponseDTO<ClientDTO>(
                RESTStatusEnum.INTERNAL_SERVER_ERROR.code,
                null
            );
        }
    }
    
    @PostMapping("/withdraw")
    public APIResponseDTO<ClientDTO> withdraw(@RequestBody WithdrawDTO withdrawDTO, HttpServletResponse response) {
        try{
            ClientDTO client = business.withdraw(withdrawDTO);

            response.setStatus(RESTStatusEnum.OK.code); 
            return new APIResponseDTO<ClientDTO>(
                RESTStatusEnum.OK.code,
                client
            );

        } catch(Exception e){
            response.setStatus(RESTStatusEnum.INTERNAL_SERVER_ERROR.code); 
            return new APIResponseDTO<ClientDTO>(
                RESTStatusEnum.INTERNAL_SERVER_ERROR.code,
                null
            );
        }
    }
    
    @PostMapping("/transferBetweenClients")
    public APIResponseDTO<String> transferBetweenClients(@RequestBody TransferDTO transferDTO, HttpServletResponse response) {
        try{
            String successMessage = business.transferBetweenClients(transferDTO);

            response.setStatus(RESTStatusEnum.OK.code); 
            return new APIResponseDTO<String>(
                RESTStatusEnum.OK.code,
                successMessage
            );

        } catch(APIException apiException){
            response.setStatus(apiException.statusCode); 
            return new APIResponseDTO<String>(
                apiException.statusCode,
                apiException.getMessage()
            );            

        }catch(Exception exception){
            response.setStatus(RESTStatusEnum.INTERNAL_SERVER_ERROR.code); 
            return new APIResponseDTO<String>(
                RESTStatusEnum.INTERNAL_SERVER_ERROR.code,
                null
            );
        }
    }
}