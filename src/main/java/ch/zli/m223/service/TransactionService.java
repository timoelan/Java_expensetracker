package ch.zli.m223.service;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import ch.zli.m223.model.Transaction;
import java.util.List;

@ApplicationScoped
public class TransactionService {

    @Inject 
    EntityManager entityManager;

    @Transactional
    public Transaction createTransaction(Transaction transaction){
        entityManager.persist(transaction);
        return transaction;
    }

    @Transactional
    public List<Transaction> findAll(){
        var query = entityManager.createQuery("FROM Transaction", Transaction.class);
        return query.getResultList();
    }

    @Transactional
    public void deleteTransaction(Long id){
        var transaction = entityManager.find(Transaction.class, id);
        if (transaction != null) {
            entityManager.remove(transaction);
        }
    }

    @Transactional
    public Transaction updateTransaction(Long id, Transaction updatedTransaction) {
        Transaction existingTransaction = entityManager.find(Transaction.class, id);
        
        if (existingTransaction == null) {
            return null;
        }
        
        existingTransaction.setAmount(updatedTransaction.getAmount());
        existingTransaction.setDescription(updatedTransaction.getDescription());
        existingTransaction.setDate(updatedTransaction.getDate());
        
        if (updatedTransaction.getUser() != null) {
            existingTransaction.setUser(updatedTransaction.getUser());
        }
        if (updatedTransaction.getCategory() != null) {
            existingTransaction.setCategory(updatedTransaction.getCategory());
        }
        
        return existingTransaction;
    }
    
    public Transaction findById(Long id) {
        return entityManager.find(Transaction.class, id);
    }
}
