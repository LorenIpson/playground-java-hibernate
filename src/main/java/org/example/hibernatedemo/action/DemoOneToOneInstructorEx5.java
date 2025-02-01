package org.example.hibernatedemo.action;

import org.example.hibernatedemo.model.one_to_one.Instructor;
import org.example.hibernatedemo.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * 有 Cascade 的狀況下，把物件轉移到 Removed 狀態。注意 ...Detail 沒有 Cascade，通常只會寫一邊。
 * 沒有 Cascade 的狀況下，把物件轉移到 Removed 狀態。
 */
public class DemoOneToOneInstructorEx5 {

    public static void main(String[] args) {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            Instructor ins1 = session.find(Instructor.class, 3);

            session.remove(ins1);
            // session.find 標記為 persist 狀態，session.remove 標記為 removed 狀態，所以不需要再設定為 persist 狀態。
            // 當 session.find 的物件做了修正，像是更改 email 或 phone number，才需要再設定為 persist 狀態。
            // session.persist(ins1);

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
