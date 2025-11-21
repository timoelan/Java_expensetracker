package ch.zli.m223.service;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import ch.zli.m223.model.User;
import java.util.List;

@ApplicationScoped
public class UserService {

    @Inject 
    EntityManager entityManager;

    @Transactional
    public User createUser(User user){
        entityManager.persist(user);
        return user;
    }

    @Transactional
    public List<User> findAll(){
        var query = entityManager.createQuery("FROM User", User.class);
        return query.getResultList();
    }

    @Transactional
    public void deleteUser(Long id){
        var user = entityManager.find(User.class, id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    @Transactional
    public User updateUser(Long id, User updatedUser) {
        User existingUser = entityManager.find(User.class, id);
        
        if (existingUser == null) {
            return null;
        }
        
        if (updatedUser.getName() != null) {
            existingUser.setName(updatedUser.getName());
        }
        if (updatedUser.getEmail() != null) {
            existingUser.setEmail(updatedUser.getEmail());
        }
        
        return existingUser;
    }
    
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }
}
