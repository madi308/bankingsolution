package com.tuum.bankingsolution.controller;

import com.tuum.bankingsolution.dto.TransactionDto;
import com.tuum.bankingsolution.dtomapper.DtoTransactionMapper;
import com.tuum.bankingsolution.model.Transaction;
import com.tuum.bankingsolution.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    private final DtoTransactionMapper dtoTransactionMapper;

    @GetMapping("/{id}")
    public ResponseEntity<List<TransactionDto>> getTransactions(@PathVariable long id) {
        List<Transaction> transactions = transactionService.getTransactions(id);
        return ResponseEntity.ok(dtoTransactionMapper.entitiesToDtoList(transactions));
    }

    @PostMapping
    public ResponseEntity<TransactionDto> addTransaction(@RequestBody TransactionDto transactionDto) {
        Transaction draftTransaction = dtoTransactionMapper.dtoToEntity(transactionDto);
        Transaction transaction = transactionService.addTransaction(draftTransaction);
        return ResponseEntity.status(CREATED)
                .body(dtoTransactionMapper.entityToDto(transaction));
    }
}
