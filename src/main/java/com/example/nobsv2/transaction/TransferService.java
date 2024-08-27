package com.example.nobsv2.transaction;

import com.example.nobsv2.Command;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class TransferService implements Command<TransferDTO, String> {

    private final BankAccountRepository bankAccountRepository;

    public TransferService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public ResponseEntity<String> execute(TransferDTO transfer) {
        Optional<BankAccount> fromAccount = bankAccountRepository.findById(transfer.getFromUser());
        Optional<BankAccount> toAccount = bankAccountRepository.findById(transfer.getToUser());

        if(fromAccount.isEmpty() || toAccount.isEmpty()){
            throw new RuntimeException("User not found"); //this can be a Custom Exception
        }

        BankAccount from = fromAccount.get();
        BankAccount to = toAccount.get();

        add(to, transfer.getAmount());
        deduct(from, transfer.getAmount());

        return ResponseEntity.ok("Success");
    }

    private void deduct(BankAccount bankAccount, double amount){
        if(bankAccount.getBalance() < amount){
            throw new RuntimeException("Not enough money");//this must be a runtime exception or extend it
        }
        bankAccount.setBalance(bankAccount.getBalance() -  amount);
    }

    private void add(BankAccount bankAccount, double amount){
        bankAccount.setBalance(bankAccount.getBalance() + amount);
    }
}
