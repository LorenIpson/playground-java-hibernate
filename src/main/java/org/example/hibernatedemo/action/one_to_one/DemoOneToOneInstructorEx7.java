package org.example.hibernatedemo.action.one_to_one;

import org.example.hibernatedemo.model.one_to_one.Instructor;
import org.example.hibernatedemo.model.one_to_one.InstructorDetail;
import org.example.hibernatedemo.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * 沒有 cascade 狀況下做刪除，只知道 Giovanni 是 8 號
 */
public class DemoOneToOneInstructorEx7 {

    public static void main(String[] args) {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            Instructor ins8 = session.find(Instructor.class, 8);
            InstructorDetail ins8Detail = ins8.getInstructorDetail();

            // 因為外鍵約束，要注意順序。
            session.remove(ins8);
            session.remove(ins8Detail);

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
