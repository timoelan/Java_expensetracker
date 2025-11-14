package ch.zli.m223.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

import ch.zli.m223.model.Tag;

@ApplicationScoped
public class TagService {

    @Inject
    private EntityManager entityManager;

    
    @Transactional
    public Tag createTag(Tag tag){
        entityManager.persist(tag);
        return tag;
    }

    @Transactional
    public List<Tag> findAll(){
        var query = entityManager.createQuery("FROM Tag", Tag.class);
        return query.getResultList();
    }

    @Transactional
    public Tag updateTag(Long id, Tag tag){
        var existingTag = entityManager.find(Tag.class, id);
        existingTag.setTitle(tag.getTitle());
        return existingTag;
    }
    
    @Transactional
    public void deleteTag(Long id){
        var tag = entityManager.find(Tag.class, id);
        entityManager.remove(tag);
    }
}
