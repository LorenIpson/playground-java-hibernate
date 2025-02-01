package org.example.hibernatedemo.action;

import org.example.hibernatedemo.model.one_to_one.Instructor;
import org.example.hibernatedemo.model.one_to_one.InstructorDetail;
import org.example.hibernatedemo.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * 新增資料進入資料庫。
 * */
public class DemoOneToOneInstructorEx1 {

    public static void main(String[] args) {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            // Instructor 有 Cascade。
            Instructor instructor1 = new Instructor();
            instructor1.setName("Andy");

            InstructorDetail instructorDetail = new InstructorDetail();
            instructorDetail.setEmail("andy@mail.com");
            instructorDetail.setPhone("0987654321");

            // 進行關聯。
            // instructorDetail.setInstructor(instructor1);
            instructor1.setInstructorDetail(instructorDetail);

            // 寫在有 Cascade 的那一邊。
            session.persist(instructor1);

            session.getTransaction().commit();
            System.out.println("YOS, committing");
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println("Nah, I'd rollback");
            throw new RuntimeException(e);
        } finally {
            HibernateUtil.closeSessionFactory();
        }

    }
}
