package com.emma.banking_app.dtos;

import com.emma.banking_app.entities.Operation;
import lombok.Data;

import java.util.List;

@Data
public class AccountOperationsDto {
    private String id;
    private double solde;
    private int currentPage;
    private int totalPages;
    private int size;
    private List<OperationDto> operationDtos;
}
