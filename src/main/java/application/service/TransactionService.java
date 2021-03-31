package application.service;

import application.entity.Transaction;
import application.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction findTransactionByIdentifiantTransaction(int identifiant_transaction){
        return transactionRepository.findTransactionByIdentifiantTransaction(identifiant_transaction);
    }
}
