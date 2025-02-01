package org.example.hibernatedemo.action;

import org.example.hibernatedemo.model.one_to_one.Instructor;
import org.example.hibernatedemo.model.one_to_one.InstructorDetail;
import org.example.hibernatedemo.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * 只知道 Andy 是 Instructor 1 號，但想要改 Email。
 */
public class DemoOneToOneInstructorEx3 {

    public static void main(String[] args) {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            Instructor ins1 = session.get(Instructor.class, 1);

            if (ins1 != null) {
                InstructorDetail detail1 = ins1.getInstructorDetail();
                System.out.print("有這位使用者，原本的 Email 為：");
                System.out.println(detail1.getEmail());
                System.out.print("新的使用者 Email 為：");
                detail1.setEmail("Andretti@mail.com");
                System.out.println(detail1.getEmail());
                session.persist(ins1);
            } else {
                System.out.println("這個 ID 沒有使用者。");
            }

            session.getTransaction().commit();
            System.out.println("=======================================");
            System.out.println("YOS, committing");
            System.out.println("=======================================");
        } catch (Exception e) {
            session.getTransaction().rollback();
            System.out.println("=======================================");
            System.out.println("Nah, I'd rollback");
            System.out.println("=======================================");
            throw new RuntimeException(e);
        } finally {
            HibernateUtil.closeSessionFactory();
        }

    }
}
