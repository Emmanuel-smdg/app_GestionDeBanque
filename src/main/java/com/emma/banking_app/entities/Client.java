package com.emma.banking_app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class Client {

    private Long id ;
    private String name ;
    private String email ;
    private List <Compte> comptes ;
}
