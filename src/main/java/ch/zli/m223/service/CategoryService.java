package ch.zli.m223.service;

import ch.zli.m223.model.Category;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;


import java.util.List;


@ApplicationScoped
public class CategoryService {
    @Inject
    private EntityManager entityManager;

    @Transactional
    public Category createCategory(Category category){
        entityManager.persist(category);
        return category;
    }
    
    @Transactional
    public List<Category> findAll() {
        var createQuery = entityManager.createQuery("FROM Category", Category.class);
        return createQuery.getResultList();
    }

    @Transactional
	public void deleteCategory(Long id) {
		var deleteQuery = entityManager.find(Category.class, id);
        entityManager.remove(deleteQuery);
	}

    @Transactional
    public Category updateCategory(Long id, Category category){
        var existingCategory = entityManager.find(Category.class, id);
        existingCategory.setTitle(category.getTitle());
        return entityManager.merge(existingCategory);
    }


}
