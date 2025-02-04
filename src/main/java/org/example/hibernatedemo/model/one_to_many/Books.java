package org.example.hibernatedemo.model.one_to_many;

import jakarta.persistence.*;

@Entity
@Table(schema = "OneToMany", name = "books")
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "booktitle")
    private String title;

    @Column(name = "publicyear")
    private String year;

    // 對面只有一筆。
    // @OneToMany(fetch = FetchType.LAZY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user_id")
    private BookUsers bookUsers;

    public Books() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public BookUsers getBookUsers() {
        return bookUsers;
    }

    public void setBookUsers(BookUsers bookUsers) {
        this.bookUsers = bookUsers;
    }
}
