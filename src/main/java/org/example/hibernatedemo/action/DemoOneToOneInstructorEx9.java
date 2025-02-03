package org.example.hibernatedemo.action;

import org.example.hibernatedemo.model.one_to_one.Instructor;
import org.example.hibernatedemo.model.one_to_one.InstructorDetail;
import org.example.hibernatedemo.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * 只知道 Becca 是 instructor id=5 ，把 email 和 phone 加回去。
 * */
public class DemoOneToOneInstructorEx9 {

    public static void main(String[] args) {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            Instructor ins5 = session.find(Instructor.class, 5);
            InstructorDetail ins5Detail = new InstructorDetail();
            ins5Detail.setEmail("Becca@mail.com");
            ins5Detail.setPhone("0912876543");

            // 要記得設定為 persist 狀態。
            session.persist(ins5Detail);
            ins5.setInstructorDetail(ins5Detail);

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
