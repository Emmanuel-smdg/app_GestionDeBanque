package com.emma.banking_app.services;

import com.emma.banking_app.entities.Client;
import com.emma.banking_app.entities.Compte;
import com.emma.banking_app.entities.CompteCourant;
import com.emma.banking_app.entities.CompteEpargne;
import com.emma.banking_app.exceptions.ClientNotFoundException;
import com.emma.banking_app.exceptions.CompteNotFoundException;
import com.emma.banking_app.exceptions.SoldeIssufisantException;

import java.util.List;

public interface BankAccountService {
    Client saveClient(Client client);
    CompteCourant saveCurrentAccount(double soldeInitial, double decouvert, Long idClient) throws ClientNotFoundException;
    CompteEpargne saveEpargneAccount(double soldeInitial, double taux, Long idClient) throws ClientNotFoundException;

    List<Client> listClient();
    Compte getAccount(String accountId) throws CompteNotFoundException;
    void debit(String compteId, double montant, String description) throws CompteNotFoundException, SoldeIssufisantException;
    void credit(String compteId, double montant, String description) throws CompteNotFoundException;

    void transfert(String compteSource, String compteDestinataire, double montant) throws CompteNotFoundException, SoldeIssufisantException;

    List<Compte> listeCompte();
}
