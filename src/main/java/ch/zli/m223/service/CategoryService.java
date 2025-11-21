package ch.zli.m223.service;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import ch.zli.m223.model.Category;
import java.util.List;

@ApplicationScoped
public class CategoryService {

    @Inject 
    EntityManager entityManager;

    @Transactional
    public Category createCategory(Category category){
        entityManager.persist(category);
        return category;
    }

    @Transactional
    public List<Category> findAll(){
        var query = entityManager.createQuery("FROM Category", Category.class);
        return query.getResultList();
    }

    @Transactional
    public void deleteCategory(Long id){
        var category = entityManager.find(Category.class, id);
        if (category != null) {
            entityManager.remove(category);
        }
    }

    @Transactional
    public Category updateCategory(Long id, Category updatedCategory) {
        Category existingCategory = entityManager.find(Category.class, id);
        
        if (existingCategory == null) {
            return null;
        }
        
     
        if (updatedCategory.getName() != null) {
            existingCategory.setName(updatedCategory.getName());
        }
        if (updatedCategory.getDescription() != null) {
            existingCategory.setDescription(updatedCategory.getDescription());
        }
        
       
        return existingCategory;
    }
    
    public Category findById(Long id) {
        return entityManager.find(Category.class, id);
    }
}
