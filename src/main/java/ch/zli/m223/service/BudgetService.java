package ch.zli.m223.service;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import ch.zli.m223.model.Budget;
import java.util.List;

@ApplicationScoped
public class BudgetService {

    @Inject
    EntityManager entityManager;

    @Transactional
    public Budget createBudget(Budget budget){
        entityManager.persist(budget);
        return budget;
    }

    @Transactional
    public List<Budget> findAll(){
        var query = entityManager.createQuery("FROM Budget", Budget.class);
        return query.getResultList();
    }

    @Transactional
    public void deleteBudget(Long id){
        var budget = entityManager.find(Budget.class, id);
        if (budget != null) {
            entityManager.remove(budget);
        }
    }

    @Transactional
    public Budget updateBudget(Long id, Budget updatedBudget){
        Budget existingBudget = entityManager.find(Budget.class, id);

        if (existingBudget == null) {
            return null;
        }
       
        existingBudget.setAmount(updatedBudget.getAmount());
        existingBudget.setMonth(updatedBudget.getMonth());
        existingBudget.setYear(updatedBudget.getYear());
        
        if (updatedBudget.getUser() != null) {
            existingBudget.setUser(updatedBudget.getUser());
        }
        if (updatedBudget.getCategory() != null) {
            existingBudget.setCategory(updatedBudget.getCategory());
        }

        return existingBudget;
    }

    public Budget findById(Long id) {
        return entityManager.find(Budget.class, id);
    }
}