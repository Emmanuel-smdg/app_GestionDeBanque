package com.emma.banking_app.mappers;

import com.emma.banking_app.dtos.ClientDto;
import com.emma.banking_app.dtos.CompteCourantDto;
import com.emma.banking_app.dtos.CompteEpargneDto;
import com.emma.banking_app.dtos.OperationDto;
import com.emma.banking_app.entities.Client;

import com.emma.banking_app.entities.CompteCourant;
import com.emma.banking_app.entities.CompteEpargne;
import com.emma.banking_app.entities.Operation;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BankAccountMapperImpl {

    public ClientDto fromClient(Client client){
        ClientDto clientDto = new ClientDto();

        /* Methode de transfert
        * clientDto.setnom = client.getnom
        *On recup tous les attributs voulu de client vers clientDto
        * */

        BeanUtils.copyProperties(client,clientDto);

        return clientDto;
    }

    public Client fromClientDto(ClientDto clientDto){
        Client client = new Client();
        BeanUtils.copyProperties(clientDto, client);
        return client;
    }

    public CompteEpargne fromCompteEpargneDto(CompteEpargneDto compteEpargneDto){
        CompteEpargne compteEpargne = new CompteEpargne();
        BeanUtils.copyProperties(compteEpargneDto,compteEpargne);
        compteEpargne.setClient(compteEpargneDto.getClientDto());
        return compteEpargne;
    }

    public CompteEpargneDto fromCompteEpargne(CompteEpargne compteEpargne){
        CompteEpargneDto compteEpargneDto = new CompteEpargneDto();
        BeanUtils.copyProperties(compteEpargne, compteEpargneDto);
        compteEpargneDto.setClientDto(compteEpargne.getClient());
        compteEpargneDto.setType(compteEpargne.getClass().getSimpleName());
        return compteEpargneDto;

    }

    public CompteCourant fromCompteCourantDto(CompteCourantDto compteCourantDto){
        CompteCourant compteCourant = new CompteCourant();
        BeanUtils.copyProperties(compteCourantDto,compteCourant);
        compteCourant.setClient(compteCourantDto.getClientDto());
        return compteCourant;
    }

    public CompteCourantDto fromCompteCourant(CompteCourant compteCourant){
        CompteCourantDto compteCourantDto = new CompteCourantDto();
        BeanUtils.copyProperties(compteCourant,compteCourantDto);
        compteCourantDto.setClientDto(compteCourant.getClient());
        compteCourantDto.setType(compteCourant.getClass().getSimpleName());

        return compteCourantDto ;
    }

    public OperationDto fromOperation(Operation operation){
        OperationDto operationDto = new OperationDto();
        BeanUtils.copyProperties(operation, operationDto);
        return operationDto;
    }



}
