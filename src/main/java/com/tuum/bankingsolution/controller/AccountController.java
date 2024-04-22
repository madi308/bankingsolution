package com.tuum.bankingsolution.controller;

import com.tuum.bankingsolution.dto.AccountDto;
import com.tuum.bankingsolution.dtomapper.DtoAccountMapper;
import com.tuum.bankingsolution.model.Account;
import com.tuum.bankingsolution.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final DtoAccountMapper dtoAccountMapper;

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable long id) {
        Account account = accountService.getAccount(id);
        return ResponseEntity.ok(dtoAccountMapper.entityToDto(account));
    }

    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody Map<String, Object> payload) {
        Account account = accountService.addAccount(payload);
        return ResponseEntity.status(CREATED)
                .body(dtoAccountMapper.entityToDto(account));
    }
}
