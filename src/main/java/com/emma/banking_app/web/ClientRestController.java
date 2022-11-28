package com.emma.banking_app.web;

import com.emma.banking_app.dtos.ClientDto;
import com.emma.banking_app.exceptions.ClientNotFoundException;
import com.emma.banking_app.services.BankAccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class ClientRestController {
    private BankAccountService bankAccountService ;

    @GetMapping("/clients")
    public List<ClientDto> listeClient(){
        return bankAccountService.listClient();
    }

    @GetMapping("/clients/search")
    public List<ClientDto> searchClients(@RequestParam(name = "motcle", defaultValue = "") String motcle){
        return bankAccountService.searchClients("%"+motcle+"%");
    }

    @GetMapping("/clients/{id}")
    public ClientDto getClient(@PathVariable(name = "id") Long idClient) throws ClientNotFoundException {
        return bankAccountService.getClient(idClient);
    }

    @PostMapping("/clients")
    public ClientDto saveClient(@RequestBody ClientDto clientDto){
        return bankAccountService.saveClient(clientDto) ;
    }

    @PutMapping("/clients/{idClient}")
    public ClientDto updateClient(@PathVariable Long idClient,@RequestBody ClientDto clientDto){
        clientDto.setId(idClient);
        return bankAccountService.updateClient(clientDto);
    }

    @DeleteMapping("/clients/{idClient}")
    public void deleteClient(@PathVariable Long idClient ){
        bankAccountService.deleteClient(idClient);
    }

}
