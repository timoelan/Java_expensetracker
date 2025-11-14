package ch.zli.m223.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false)
    private String title;

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }


    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private Set<Entry> entries;

    public Set<Entry> getEntries(){
        return entries;
    }
    
    public void setEntries(Set<Entry> entries){
        this.entries = entries;
    }
    
}
