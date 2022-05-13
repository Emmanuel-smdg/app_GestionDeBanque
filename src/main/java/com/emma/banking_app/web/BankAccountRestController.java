package com.emma.banking_app.web;

import com.emma.banking_app.dtos.AccountOperationsDto;
import com.emma.banking_app.dtos.CompteDto;
import com.emma.banking_app.dtos.OperationDto;
import com.emma.banking_app.exceptions.CompteNotFoundException;
import com.emma.banking_app.services.BankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class BankAccountRestController {
    private BankAccountService bankAccountService ;

    @GetMapping("/comptes")
    public List<CompteDto> listeComptes(){
        return bankAccountService.listeCompte();
    }

    @GetMapping("/comptes/{id}")
    public CompteDto getAccount(@PathVariable String id) throws CompteNotFoundException {
        return bankAccountService.getAccount(id);
    }

    @GetMapping("/comptes/{id}/operations")
    public List<OperationDto> historique(@PathVariable String id){
        return bankAccountService.historique(id);
    }

    @GetMapping("/comptes/{id}/pageOperations")
    public AccountOperationsDto CompteHistorique(@PathVariable String id,
                                                 @RequestParam (name = "page", defaultValue = "0") int page,
                                                 @RequestParam (name = "size", defaultValue = "5") int size) throws CompteNotFoundException {
        return bankAccountService.getAccountHistory(id,page,size);
    }
}
