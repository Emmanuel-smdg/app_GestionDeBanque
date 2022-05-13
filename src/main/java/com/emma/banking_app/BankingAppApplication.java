package com.emma.banking_app;

import com.emma.banking_app.dtos.ClientDto;
import com.emma.banking_app.dtos.CompteCourantDto;
import com.emma.banking_app.dtos.CompteEpargneDto;
import com.emma.banking_app.entities.Client;
import com.emma.banking_app.entities.CompteCourant;
import com.emma.banking_app.entities.CompteEpargne;
import com.emma.banking_app.entities.Operation;
import com.emma.banking_app.enums.AccountStatus;
import com.emma.banking_app.enums.OperationType;
import com.emma.banking_app.exceptions.ClientNotFoundException;
import com.emma.banking_app.exceptions.CompteNotFoundException;
import com.emma.banking_app.exceptions.SoldeIssufisantException;
import com.emma.banking_app.repositories.ClientRepository;
import com.emma.banking_app.repositories.CompteRepository;
import com.emma.banking_app.repositories.OperationRepository;
import com.emma.banking_app.services.BankAccountService;
import com.emma.banking_app.services.BankAccountServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class BankingAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankingAppApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(BankAccountService bankAccountService){
        return args -> {
            Stream.of("Omar","Yasmine","Leyman","Inoussa","Abacar","Saoudata","Leaticia").forEach(name ->{
                ClientDto clientDto = new ClientDto();
                clientDto.setName(name);
                clientDto.setEmail(name + "@gmail.com");
                bankAccountService.saveClient(clientDto); });

            bankAccountService.listClient().forEach(clt->{
                try {
                    bankAccountService.saveCurrentAccount(Math.random()*750000,Math.random()*7400, clt.getId());
                    bankAccountService.saveEpargneAccount(Math.random()*800000,5.5, clt.getId());
                    bankAccountService.listeCompte().forEach(cpte->{
                        for (int i=0; i < 2 ; i++){
                            try {
                                String cpteId;
                                if(cpte instanceof CompteEpargneDto){
                                    cpteId = ((CompteEpargneDto) cpte).getId();
                                }else cpteId = ((CompteCourantDto) cpte).getId();
                                bankAccountService.credit(cpteId,274000+Math.random()*9875,"credit ");
                                bankAccountService.debit(cpteId, 3000+Math.random()*1000,"debit");
                            } catch (CompteNotFoundException | SoldeIssufisantException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                } catch (ClientNotFoundException e) {
                    e.printStackTrace();
                }

            });

        };
    }

    //@Bean
    CommandLineRunner start(ClientRepository clientRepository, OperationRepository operationRepository,
                            CompteRepository compteRepository){
        return args -> {
            Stream.of("Omar","Yasmine","Leyman","Inoussa","Abacar","Saoudata","Leaticia").forEach(name ->{
                Client client = new Client();
                client.setName(name);
                client.setEmail(name + "@gmail.com");
                clientRepository.save(client);
            });
            clientRepository.findAll().forEach(clt-> {
                CompteCourant compteCourant = new CompteCourant();
                compteCourant.setSolde(Math.random()*750000);
                compteCourant.setCreatedAt(new Date());
                compteCourant.setDecouvert(Math.random()*7400);
                compteCourant.setStatus(AccountStatus.CREATED);
                compteCourant.setClient(clt);
                compteRepository.save(compteCourant);

                CompteEpargne compteEpargne = new CompteEpargne();
                compteEpargne.setSolde(Math.random()*780000);
                compteEpargne.setCreatedAt(new Date());
                compteEpargne.setTaux(Math.random()+5);
                compteEpargne.setStatus(AccountStatus.CREATED);
                compteEpargne.setClient(clt);
                compteRepository.save(compteEpargne);
            });
            compteRepository.findAll().forEach(cpt ->{
                for (int i=0; i < 2 ; i++){
                    Operation operation = new Operation();
                    operation.setOperationDate(new Date());
                    operation.setMontant(Math.random()*217000);
                    operation.setType(Math.random()>0.5 ? OperationType.DEBIT : OperationType.CREDIT);
                    operation.setCompte(cpt);
                    operationRepository.save(operation);
                }
            });
        };
    }

}
