package org.example.hibernatedemo.model.one_to_many;

import jakarta.persistence.*;

import java.util.LinkedList;
import java.util.List;

@Entity
@Table(schema = "OneToMany", name = "bookusers")
public class BookUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username")
    private String Name;

    @OneToMany(mappedBy = "bookUsers", cascade = CascadeType.ALL)
    private List<Books> Books = new LinkedList<>();

    public BookUsers() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<Books> getBooks() {
        return Books;
    }

    public void setBooks(List<Books> books) {
        Books = books;
    }
}
