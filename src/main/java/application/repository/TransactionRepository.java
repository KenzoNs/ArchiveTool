package application.repository;

import application.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    @Query("SELECT t FROM Transaction t WHERE t.identifiant_transaction = :identifiant_transaction")
    Transaction findTransactionByIdentifiantTransaction(@Param("identifiant_transaction") int identifiant_transaction);
}
