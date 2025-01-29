package org.example.hibernatedemo.model.one_to_one;


import jakarta.persistence.*;

@Entity
@Table(schema = "OneToOne", name = "instructorDetail")
public class InstructorDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;

    private String phone;

    // 用 Java 屬性去抓對面和我們的關係，也就是對面如何找到我們的。
    // One To One，JoinColumn 不能和 mappedBy 同時出現。
    @OneToOne(mappedBy = "instructorDetail")
    private Instructor instructor;

    public InstructorDetail() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
}
