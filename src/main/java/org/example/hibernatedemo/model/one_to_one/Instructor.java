package org.example.hibernatedemo.model.one_to_one;

import jakarta.persistence.*;

/* TODO：
 *   1. 確認 Schema 讀取狀態。
 *   2. 確認 name 是否是 Case sensitive。
 */

@Entity
@Table(schema = "OneToOne", name = "instructor")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "instructorName")
    private String name;

    // One To One，JoinColumn 不能和 mappedBy 同時出現。
    @JoinColumn(name = "fk_instructorDetail_id")
    @OneToOne(cascade = {CascadeType.ALL})
    private InstructorDetail instructorDetail;

    public Instructor() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InstructorDetail getInstructorDetail() {
        return instructorDetail;
    }

    public void setInstructorDetail(InstructorDetail instructorDetail) {
        this.instructorDetail = instructorDetail;
    }
}
