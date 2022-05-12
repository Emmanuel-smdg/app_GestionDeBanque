package com.emma.banking_app.services;

import com.emma.banking_app.entities.*;
import com.emma.banking_app.enums.OperationType;
import com.emma.banking_app.exceptions.ClientNotFoundException;
import com.emma.banking_app.exceptions.CompteNotFoundException;
import com.emma.banking_app.exceptions.SoldeIssufisantException;
import com.emma.banking_app.repositories.ClientRepository;
import com.emma.banking_app.repositories.CompteRepository;
import com.emma.banking_app.repositories.OperationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class BankAccountServiceImpl implements BankAccountService{
    private ClientRepository clientRepository;
    private CompteRepository compteRepository;
    private OperationRepository operationRepository ;

    @Override
    public Client saveClient(Client client) {
        log.info("Enregistrement d'un Client");
        Client savedClient = clientRepository.save(client);
        return savedClient;
    }

    @Override
    public CompteCourant saveCurrentAccount(double soldeInitial, double decouvert, Long idClient) throws ClientNotFoundException {
        Client client = clientRepository.findById(idClient).orElse(null);
        if(client == null)
            throw new ClientNotFoundException("Le client auquel vous voulez associé ce compte n'existe pas");

        CompteCourant compteCourant = new CompteCourant();
        compteCourant.setId(UUID.randomUUID().toString());
        compteCourant.setCreatedAt(new Date());
        compteCourant.setClient(client);
        compteCourant.setDecouvert(decouvert);
        CompteCourant savedCompteCourant = compteRepository.save(compteCourant);
        return savedCompteCourant;
    }

    @Override
    public CompteEpargne saveEpargneAccount(double soldeInitial, double taux, Long idClient) throws ClientNotFoundException {
        Client client = clientRepository.findById(idClient).orElse(null);
        if(client == null)
            throw new ClientNotFoundException("Le client auquel vous voulez associé ce compte n'existe pas");

        CompteEpargne compteEpargne = new CompteEpargne();
        compteEpargne.setId(UUID.randomUUID().toString());
        compteEpargne.setCreatedAt(new Date());
        compteEpargne.setClient(client);
        compteEpargne.setTaux(taux);
        CompteEpargne savedCompteEpargne = compteRepository.save(compteEpargne);
        return savedCompteEpargne;
    }


    @Override
    public List<Client> listClient() {
        return clientRepository.findAll();
    }

    @Override
    public Compte getAccount(String accountId) throws CompteNotFoundException {
        Compte compte = compteRepository.findById(accountId)
                .orElseThrow(()-> new CompteNotFoundException("Ce Compte n'existe pas"));
        return compte;
    }

    @Override
    public void debit(String compteId, double montant, String description) throws CompteNotFoundException, SoldeIssufisantException {
        Compte compte = getAccount(compteId);
        if(compte.getSolde() < montant)
            throw new SoldeIssufisantException("Le solde de ce compte est inssufisant pour effectuer cette opération");
        Operation operation = new Operation();
        operation.setType(OperationType.DEBIT);
        operation.setDescription(description);
        operation.setOperationDate(new Date());
        operation.setCompte(compte);
        operation.setMontant(montant);
        compte.setSolde(compte.getSolde() - montant);
        operationRepository.save(operation);
        compteRepository.save(compte);

    }

    @Override
    public void credit(String compteId, double montant, String description) throws CompteNotFoundException {
        Compte compte = getAccount(compteId);
        Operation operation = new Operation();
        operation.setType(OperationType.CREDIT);
        operation.setDescription(description);
        operation.setOperationDate(new Date());
        operation.setCompte(compte);
        operation.setMontant(montant);
        compte.setSolde(compte.getSolde() + montant);
        operationRepository.save(operation);
        compteRepository.save(compte);

    }

    @Override
    public void transfert(String compteSource, String compteDestinataire, double montant) throws CompteNotFoundException, SoldeIssufisantException {
        debit(compteSource,montant,"Transfert vers" +compteDestinataire);
        credit(compteDestinataire,montant,"Transfert de la part de" +compteSource);
    }

    @Override
    public List<Compte> listeCompte(){
        return compteRepository.findAll();
    }


}
